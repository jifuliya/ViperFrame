# ViperFrame

*[中文](README.md)|[English](README_En.md)* 

![](https://img.shields.io/badge/language-java-brightgreen.svg) ![](https://img.shields.io/badge/frame-viper-yellowgreen.svg)

## VIPER是怎么来的？

Viper起始于iOS，美团的iOS即是用VIPER进行了app的架构，而相比于Android而言，则是采用Mvp又或者是MVVM。在传统的MVC架构中，众所周知，
其controller(控制器)层接纳了太多的任务，当开发不断进行，其内部的业务逻辑逐渐积累，最后则会变得臃肿不堪，
不便于后期的调试测试以及多人协助，所以，我们需要寻找减轻controller层负担的方法，而VIPER架构其实是将controller再细分成三层，
分别是view、interactor、presenter，已达到减轻controller层负担的作用。

对于Mvp而言，presenter减轻了controller的负担，清晰了整个架构层次，view通过接口的方式来对ui进行管理，大大减轻了activity/fragment的负担，
然而在项目越来越大之后，presenter似乎也犹如controller一般，变得臃肿不堪，无数的接口让view似乎变得难以维护，换句话说，Mvp在这个时候，
也似乎开始力不从心。

而当看见了iOS的Viper框架之后，不由眼前一亮，这个框架的特性，不就刚好解决了mvp所遇到的问题吗？那么问题来了，到底什么是viper？我们能否将它用在Android之中？

#### 什么是Viper？

##### V: view 视图：

在这里并不是指传统的UIView或其子类，事实上它就是UIViewController，在前面所说到，VIPER架构主要是将MVC架构中的controller进行更加细致的划分，而view(视图)层则是主要负责一些视图的显示、布局，用户事件的接受以及转发，基本的显示逻辑处理等等工作。

##### I: interactor 交互器：

其为Viper的中心枢纽，主要负责交互的工作，例如数据的请求（网络请求、本地持久化层请求）、某些业务逻辑的处理，在这里我们得到的数据是原始数据，需要经过解析处理转换成能够直接应用于视图的视图模型数据，所以我们需要用到了下一层presenter(展示器)。

##### P: presenter 展示器：

###### persenter:

当我们在上一层Interactor(交互器)中获得原始数据后，我们需要将数据进行解析处理，比如我们在交互器中进行了网络请求，得到了json数据，若要将json中所包含的内容显示出来，我们则需要将json数据进行解析，展示器就是专注于数据的解析转换，将原始的数据转换成最终能够直接显示在试图上的视图模型数据。此外，展示器中还带有路由器Router，可以进行路由的操作。

###### interactorOutput:

主要负责在ineractor输出之后对Ui进行更新操作

##### E: Entity 

实体模型对象

##### R: Router 路由器： 

负责视图的跳转，因为使用VIPER架构需要进行各层之间的相互绑定，所以视图的跳转不能简单地使用原始的方法。

各个部分都有明确的职责，遵循单一职责原则，而相比于mvp，viper更细节化的处理了app的每个环节，
不需要像一个presenter不仅处理UI事件，UI逻辑，业务逻辑，还要处理网络和数据库查询
各个部分都有明确的职责，遵循单一职责原则，而相比于mvp，viper更细节化的处理了app的每个环节，不需要像mvp一般
一个presenter不仅处理UI事件，UI逻辑，业务逻辑，还要处理网络和数据库查询。

## 框架原理
![name](https://github.com/jifuliya/ViperFrame/blob/master/bg_viper_frame.jpg)

在viper框架中我们采用了[dagger2](https://google.github.io/dagger/)来作为注入框架，
如果对[dagger2](https://google.github.io/dagger/)有兴趣的童鞋可以自己去查看一下相关资料，这里暂且不提。

关于LifeCycle的处理，目前已经有了多种选择方案，[RxLifecycle](https://github.com/trello/RxLifecycle) 或者
[AutoDispose](https://github.com/uber/AutoDispose)都是很好的选择(本人更推荐AutoDispose，至于原因嘛~RxLifecycle的作者自己就推荐的autoDispose)，

####tips 关于网络请求的处理，如果大家使用的是rxjava2.0+retrofit2.0大礼包，大家不妨试试`RxJavaPlugins`，在suscribe的过程中，
`RxJavaPlugins.onSubscribe`属于一个必调方法，可以通过它去捕获网络请求。
```java
    RxJavaPlugins.setOnObservableSubscribe(new BiFunction<Observable, Observer, Observer>() {
            @Override
            public Observer apply(Observable observable, Observer observer) throws Exception {
                return new DownObserverWraper(observer);
            }
        });
```
而DownObserverWraper则是自己定义的一个Observer<T>, 又扯远了~ 言归正传，说了这么多，没例子你说个xx？

##### 好吧，我们就拿登录来做一个流程说明，并拿mvp来做一个对比

首先，在登录的时候，会先点击登录按钮 ，presenter接口中的方法主要对api的触发时间
进行统一管理，这里既是点击登录的操作。

我们在登录页面，点击了登录之后，首先要请求登录的api，这个时候，我们采用interactor去做api请求，这里`viperModule.getData()`可以当做是请求登录api
```java
public class MainViperInteractor
        extends IBaseViperInteractor<MainContracts.MainInteractorOutputImp, MainViperModule>
        implements MainContracts.MainInteractorImp {

    @Override
    public String loadData(String string) {
        return viperModule.getData();
    }
}
```
当请求了之后，我们会返回对应的请求结果，这个时候就需要interactorOutput来控制对应的状态，在这里我们viper框架中的presenter实现了interactorOutput
和presenter两个接口方法，在presenter中，我们调用对应interactorOutput的方法来对interactor的结果进行处理。
```java
public class MainViperPresenter
        extends IBaseViperPresenter<MainContracts.MainViewImp, MainViperInteractor, MainViperModule, MainViperRouter>
        implements MainContracts.MainPresenterImp, MainContracts.MainInteractorOutputImp {

    private Disposable disposable;

    public void show() {
        //TODO interactorOutput
        viperView.showToast(viperInteractor.loadData("i come from viperInteractor!"));
    }

    public void click() {
        viperRouter.gotoActivity();
    }

    public void onLoginButtonPressed(){
        //TODO 登录点击
    }
}
```
在请求成功之后，我们会进行页面跳转，我们使用router来进行跳转以及数据绑定的逻辑处理`viperRouter.gotoActivity()`，而view则是作为整体的UIController
来统一分发UI相关的更新事件，由此可见，viper中的每一个环节，都有对应的元素来进行管控，满足单一职责原则。

试想一下，如果换做mvp，那么上面的点击，请求api，结果处理，跳转都将在presenter中进行处理，就好比又当爹又当妈，这样它能不累吗？如果再来一个房贷，一个车贷
，那估计只有去卖血维持生计了~

### 当然，并不是说mvp不好，每一个架构我都认为是十分经典的，都有它适用的场景，对于框架而言，没有好不好，只有合不合适，所以请大家仁者见仁智者见智，
根据自己的项目需求，来选择适合的框架，千万不要为了使用而使用，目前viper框架android版还属于孵化期，肯定有很多不合理的地方，欢迎大家不吝自己的宝贵意见。

## License
```
Copyright 2018 jifuliya

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```



