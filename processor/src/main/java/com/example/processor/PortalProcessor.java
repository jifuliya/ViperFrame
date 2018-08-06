package com.example.processor;

import com.example.annotation.PortalRouter;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.security.PublicKey;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
public class PortalProcessor extends AbstractProcessor {

    private static final String SUFFIX = "_Adapter";
    private static final String ACTIVITY_BIND = "getClsName";
    private static final String ACTION_BIND = "getPortalAction";

    private Messager mMessager;
    private Elements mElementUtils;
    private Filer mFiler;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);

        mElementUtils = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
        mFiler = processingEnv.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(PortalRouter.class);

        if (elements == null || elements.isEmpty()) {
            info(">>> elements is null... <<<");
            return true;
        }

        for (Element annotatedElement : elements) {

            if (annotatedElement.getKind() != ElementKind.CLASS) {
                error(annotatedElement, "Only classes can be annotated with @%s",
                        PortalRouter.class.getSimpleName());
                return true;
            }

            PackageElement packageElement = mElementUtils.getPackageOf(annotatedElement);
            String pkName = packageElement.getQualifiedName().toString();

            info(String.format("package = %s", pkName));

            String clsName = annotatedElement.getSimpleName().toString();
            String action = annotatedElement.getAnnotation(PortalRouter.class).action();

            generate(pkName + "." + clsName, clsName + SUFFIX, pkName, action);
        }
        return true;
    }

    private void generate(String clsName,
                          String newClsName,
                          String pkName,
                          String action,
                          MethodSpec methodSpec) {
        TypeSpec bind_activity = TypeSpec.classBuilder(newClsName)
                .addModifiers(Modifier.PUBLIC)
                .addMethod(whatsMyName(ACTIVITY_BIND, clsName))
                .addMethod(whatsMyName(ACTION_BIND, action))
                .build();


        JavaFile javaFile = JavaFile.builder(pkName, bind_activity)
                .build();

        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MethodSpec whatsMyName(String name, String clsName) {
        return MethodSpec.methodBuilder(name)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(String.class)
                .addStatement("return $S", clsName)
                .build();
    }

    private MethodSpec save(){

    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(PortalRouter.class.getCanonicalName());
        return annotations;
    }


    private void error(Element e, String msg, Object... args) {
        mMessager.printMessage(
                Diagnostic.Kind.ERROR,
                String.format(msg, args),
                e);
    }

    private void info(String msg, Object... args) {
        mMessager.printMessage(
                Diagnostic.Kind.NOTE,
                String.format(msg, args));
    }
}
