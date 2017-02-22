---
title: DataBinding- Say Goodye to FindViewById
date: 2017-02-17 09:55:48
tags:
    - databingding
categories:
    - Android
    
---


# DataBinding介绍

Data binding 在2015年7月发布的Android Studio v1.3.0 版本上引入，在2016年4月Android Studio v2.0.0 上正式支持。目前为止，Data Binding 已经支持双向绑定了。

Databinding 是一个实现数据和UI绑定的框架，是一个实现 MVVM 模式的工具，有了 Data Binding，在Android中也可以很方便的实现MVVM开发模式。

Data Binding 是一个support库，最低支持到Android 2.1（API Level 7+）。

<!-- more -->

Data Binding 之前，我们不可避免地要编写大量的毫无营养的代码，如 findViewById()、setText()，setVisibility()，setEnabled() 或 setOnClickListener() 等，通过 Data Binding , 我们可以通过声明式布局以精简的代码来绑定应用程序逻辑和布局，这样就不用编写大量的毫无营养的代码了。

文中的示例代码地址[DataBindingSamples](https://github.com/LeoPoldCrossing/DataBindingSamples)

---

# DataBinding简单使用示例
## DataBinding环境
App Module - build.gradle中开启dataBinding
```
android{
    ...
    dataBinding{
        enable = true
    }
}
```

## 修改Layout文件
使用dataBingding需要修改Layout文件，根标签不再是线性布局，相对布局等，而是使用layout标签
```

<layout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools">
        <!-- 原根节点 -->
        <LinearLayout>
        ...
        </LinearLayout>
</layout>
```
## 数据对象

就是一个Model，就不贴代码了

## UI绑定
* 修改布局文件,定义variable

```
<layout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    >
    <data>
    
        <variable
            name="user"
            type="com.example.databindingsamples.model.User" />
        # 也可以写成
        <import type="com.example.databindingsamples.model.User" />
        <variable name="user" type="User" />
        
    </data>
...

    <TextView
            ...
            # 使用 variable
            android:text="@{user.firstName}"
            ...
            />

        <TextView
            ...
            android:text="@{user.lastName}"
            ... />
         <TextView
            ...
            android:visibility="@{!user.adult ? View.VISIBLE:View.GONE}"
            ... />
</layout>
```
* 绑定 variable
修改`onCreate`方法 使用`DataBingdingUtil.setContentView`
```
protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityBasicBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_basic);
    User user = new User("leo", "wang", 20);
    binding.setUser(user);
    # 或者使用setVariable()
    binding.setVariable(BR.user,user)
}
```
* `ActivityBasicBinding`类是自动生成的，所有的`set`方法也是根据`variable`名称生成的。
```
# 类名生成
activity_basic.xml -> ActivityBasicBinding
# set方法生成
user -> setUser(User user)
```

## 事件绑定

### 创建代理类
先来创建一个事件的处理类，并在`layout`文件中声明。
知道大家都是好奇宝宝，先忍会哈~ 
```
public class EventHandler {
    public void onDisplayNameClick(View view) {
        Toast.makeText(view.getContext(), "DisplayName : " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
    }

    public void onFirstNameClick(User user) {
        Toast.makeText(BasicActivity.this, "FirstName : " + user.getFirstName(), Toast.LENGTH_SHORT).show();
    }

    public void onLastNameClick(View view, User user) {
        Toast.makeText(BasicActivity.this, "LastName : " + user.getLastName(), Toast.LENGTH_SHORT).show();
    }

    public void onAgeClick(View view) {
        if (user.getAge() > 18) {
            user.setAge(16);
        } else {
            user.setAge(28);
        }
        binding.setUser(user);
    }
}
```
### Method Reference
事件绑定
```
android:onClick="@{handler.onDisplayNameClick}"
```
> Tip:引用的方法参数必须和事件回调参数一致
比如 android:onClick , 引用的方法参数必须为（View view）

### Listener Binding
监听器绑定
```
android:onClick="@{()-> handler.onFirstNameClick(user)}"

android:onClick="@{(view)-> handler.onLastNameClick(view,user)}"
```

> Tip: 可自定义传递参数

### 绑定 handler 
```
binding.setHandler(new EventHandler());)
```

# 布局细节
## Imports
* Java代码中一样在xml文件中 import class 
```
<data>
    <imports type="android.view.View"/>
</data>
```
```
<TextView
    android:visibility="@{user.isAdult?View.VISIBLE:View.GONE}"
/>
```
* 类型别名
如果在data节点导入两个同名类，使用`alias`属性，给类赋一个别名
```
<import type="com.example.home.data.User" />
<import type="com.examle.detail.data.User" alias="DetailUser" />
<variable name="detailUser" type="DetailUser" />
<variable name="user" type="User" />

```
* 表达式使用导入的类型
```

<data>
    <import type="com.example.User"/>
    <import type="java.util.List"/>
    <variable name="user" type="User"/>
    <variable name="userList" type="List&lt;User&gt"/>
</data>
```

* 静态字段和方法:
```
<data>
     <import type="com.example.databindingsamples.utils.MyStringUtils" alias="StringUtil"/>
</data>
…
<TextView
   android:text="@{MyStringUtils.capitalize(user.lastName)}"
   android:layout_width="wrap_content"
   android:layout_height="wrap_content"/>
```

## Variables 
`data` 标签下可以定义任意数量的 `variable` 标签，每一个`variable`标签都描述一个可以在binding表达式中使用的变量。
```
<data>
    <import type="android.graphics.drawable.Drawable"/>
    <variable name="user" type="com.example.User"/>
    <variable name="image" type="Drawable"/>
    <variable name="note" type="String"/>
</data>
```
变量类型会在编译时检查，所以如果一个变量实现了了Observable 或者 是一个 Observable 集合，它会被反射调用。

如果变量声明的是一个未实现的Observable基类或者接口，该变量不会被观察，也就是变量的改动不会改变UI。

binding 类自动生成会为每一个变量自动生成 getter 和 setter 方法。在 setter 方法没有调用之前，他们都被设置为默认值：Object 设置为 null ， int 设置为 0 ， boolean 设置为 false 等等。。。

## Binding类自定义
* 自定义类名
```
# 生成的binding类位于databinding包下
<!--<data class="ContactBinding">-->
```
* 自定义类名，并修改生成路径
```
<!--<data class=".ContactBinding">-->
```
* 自定义类名和生成路径
```
<data class="com.example.databindingsamples.ContactBinding">
```

## Includes
在使用应用命名空间的布局中，变量可以传递到任何 include 布局中。
```
# Root添加命名空间
xmlns:bind="http://schemas.android.com/apk/res-auto
# 绑定数据

<include
    android:id="@+id/layout_user"
    layout="@layout/include_user"
    bind:user="@{user}" />
```
> Tip：
1. 需要注意的是`user`变量必须在 include 的布局中声明。
2. 如果在非根节点的 ViewGroup 中使用 include 会导致 crash

## ViewStubs
> ViewStub 是一种不可见的，0尺寸在运行时懒加载的View。当其设置为visible或者调用了inflate()方法时，它会被加载完成的view或者views替换掉。因此，ViewStub 在 setVisibility(int) 或者 inflate() 方法被调用后在 hierarchy 中就不存在了。加载后的 view 会被添加到Viewstub的父容器中，并且参数为ViewStub的布局参数。 

因为 ViewStub 会被移除，且 Binding 类中的 View 全部都是 final 修饰，所以 Binding 类中使用 ViewStubProxy 来代替 ViewStub， 开发者可以通过 ViewStubProxy 来获取 viewStub 或者 viewstub 填充后的 view。 
```
this.viewstub = new android.databinding.ViewStubProxy((android.view.ViewStub) bindings[1]);
```
inflate 一个新的 layout 时，会为新的 layout 创建一个新 binding 对象。因此，ViewStubProxy 必须监听 ViewStub 的 ViewStub.OnInflateListener，并及时建立 binding。由于 ViewStub 只能有一个 OnInflateListener，你可以将你自己的 listener 设置在 ViewStubProxy 上，在 binding 建立之后， listener 就会被触发。
```
binding.viewStub.setOnInflateListener(new ViewStub.OnInflateListener() {
            @Override
            public void onInflate(ViewStub stub, View inflated) {
                ViewStubBinding viewStubBinding = DataBindingUtil.bind(inflated);
                Contact contact = new Contact("Messi", "122134567", "2345@gmail.com");
                viewStubBinding.setContact(contact);
            }
        });
```
# 表达式
## Common Features
* 数学计算 `+ - * %`
* 字符串链接 `+` 
* 逻辑 `|| &&`
* 二进制 `& | ^`
* 一元 `+ - ! ~`
* 位移 `>> >>> << <<<`
* 比较 `> < >= <= ==`
* instance of
* Grouping()
* 字面量 `字符 字符串 数字 null`
* 类型转换
* 方法调用 使用`.`或者 `::`
* Field 访问 
* Array 访问
* 三元运算符
```
Examples:
android:text="@{String.valueOf(index + 1)}"
android:visibility="@{age<13?View.GONE:View.VISIBLE}"
android:transitionName='@{"image_" + id}'
```

## 缺失的操作符
* `this`
* `super`
* `new` 
* 显示泛型调用`<T>`
* 缺省 无法访问 this,super,new，显示泛型调用

## Null 合并运算符
```
android:text='@{user.displayName ?? "displayName is null"}'
等同于
android:text='@{user.displayName != null ? user.displayName : "displayName is null"}'
```
## 属性引用
JavaBean 引用，当表达式引用了一个类内的属性时，他会尝试直接调用域，getter，还有ObservableFields
```
android:text="@{user.firstName}"
```
## 避免 NullPointerException 
自动生成的 data binding 代码会自动检查和避免 NullPointerException.
```
@{user.name}
如果 user 为 null，则 user.name 赋予默认值 null
@{user.age} 
如果 user 为 null，则 user.age 赋予默认值 0
```
```
# 分析ActivityMainBinding 源码，看到有对user是否为null的判断
if ((dirtyFlags & 0x6L) != 0) {
    if (user != null) {
        // read user.firstName
        firstNameUser = user.getFirstName();
        // read user.lastName
        lastNameUser = user.getLastName();
    }
}
```
## 容器类
通用的容器类，数组，lists，sparse lists 和 map，可以用[]操作符来存取
```
<data>
    <import type="android.util.SparseArray"/>
    <import type="java.util.Map"/>
    <import type="java.util.List"/>
    <variable name="list" type= "List&lt;String&gt;"/>
    <variable name="sparse" type="SparseArray&lt;String&gt;"/>
    <variable name="map" type="Map&lt;String,String&gt;"/>
    <variable name="index type="int"/>
    <variable name="key" type="String"/>
</data>
...
android:text="@{list[index]}"
...
android:text="@{sparse[index]}"
...
android:text="@{map[key]}"
```

## 字符串
使用单引号将属性值括起来，就可以在表达式中使用双引号
```
android:text='@{map["firstName"]}'
```
也可以用双引号将属性值括起来，然后字符串使用&`quot；`或者反引号`来调用
```
android:text="@{map[&quot;firstName&quot;]}"
android:text="@{map[`firstName`]}"
```

## 资源 
普通的语法在表达式中访问资源 [**官方教程的坑**](http://blog.csdn.net/feelang/article/details/46342699)
```
android:padding=@{large? (int)@dimen/largePadding: (int)@dimen/smallPadding}

android:background="@{large? @color/red:@color/green}"

android:text="@{@string/nameFormat(firstName,lastName)}"

android:text="@{@plurals/banana(bananaCount,bananaCount)}"
```

需要显示声明的资源
![屏幕快照 2017-02-13 18.14.05.png-32.8kB][2]
# 数据对象 
DataBinding 让我决定引入到项目中的原因是它具备**数据改变，UI随之更新**的能力。
Data Binding 为我们提供了三种数据变动通知机制：`Observable Objects，Observable fields，Observable collections`。

Come on！ Baby~ 让我们一起看一下这三种机制的神奇之处。
## Observable Objects 
我们只需要修改Model类，数据改变后，更新UI的事情由Binding完成，再也不用费时费力的去写`binding.setXXX`~so cool！
`@Bindable`在编译时会在BR类内生成一个元素。BR类会在生成在 module package下。
```
public class ObservableUser extends BaseObservable {
    private String name;
    private long idNumber;
    private int age;


    @Bindable
    public long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(long idNumber) {
        this.idNumber = idNumber;
        notifyPropertyChanged(BR.idNumber);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyChange();
    }
}
```
## Observable Fields
如果我们的Model类里面只有少量的 Field 或者 想要节省时间，可以使用`Observable Field`及其派生的 `ObservableBoolean`,`ObservableByte`,`ObservableChar`,`ObservableShort`,`ObservableInt`,`ObservableLong`,`ObservableFloat`,`ObservableDouble`,`ObservableParcelable`。
ObaservableField自包含obsevable对象，并且只有一个Field。

* Step 1 Model 定义 ObservableField
```
public final ObservableField<String> name = new ObservableField<String>();
public final ObservableLong NO = new ObservableLong();
public final ObservableInt age = new ObservableInt();
```
* Step 2 Field Value 的set和get
```
observableFieldUser.name.set("James");
observableFieldUser.NO.set(23);
observableFieldUser.age.set(31);

observableFieldUser.name.get();
observableFieldUser.age.get();
observableFieldUser.NO.get();
```

## Observable Collections

### ObservableMap
```
# 创建
private ObservableMap<String, Object> observableArrayMap = new ObservableArrayMap<>();

# 数据
observableArrayMap.put("system", "Android");
observableArrayMap.put("brand", "三星");
observableArrayMap.put("version", "6.0.1");

# 使用
<import type="android.databinding.ObservableMap"/>

<variable
    name="map"
    type="ObservableMap&lt;String,Object&gt;"/>

android:text='@{@string/mapFormatString(map["system"],map[`brand`],map["version"])}' 
```

### ObservableList
```
# 创建
private ObservableList<Object> observableArrayList = new ObservableArrayList();

# 数据
observableArrayList.add(0, "Android");

# 使用
<import type="android.databinding.ObservableList"/>

<variable
    name="list"
    type="ObservableList&lt;Object&gt;" />

android:text="@{@string/listFormatString(list[0])}"
```

# 高级 Binding 

## Dynamic Variables 
以 RecyclerView 为例， Adapter 的 Databinding 需要动态生成，这时我们就需要动态创建 Binding 。

* 在 OnCreateViewHolder 中创建 binding ，在 onBindViewHolder 中获取 binding。

```
@Override
    public DynamicBindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.dynamic_list_item, parent, false);
        DynamicBindingViewHolder holder = new DynamicBindingViewHolder(viewDataBinding.getRoot());
        holder.setBinding(viewDataBinding);
        return holder;
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public void onBindViewHolder(DynamicBindingViewHolder holder, int position) {
        User user = users.get(position);
        holder.getBinding().setVariable(BR.user, user);
        holder.getBinding().executePendingBindings();
    }
```
* 构建Holder时直接绑定view
```
public DynamicBindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_list_item, parent,false);
    return new DynamicBindingViewHolder(inflate);
}

@Override
public void onBindViewHolder(DynamicBindingViewHolder holder, int position) {
    User user = users.get(position);
    holder.bind(user);
}
```
```
public DynamicBindingViewHolder(View itemView) {
    super(itemView);
    binding = DataBindingUtil.bind(itemView);
}

public void bind(User user){
    binding.setUser(user);
}
```

## Attribute Setters 
写过自定义控件的童鞋都知道，自定义属性需要在`attrs` 文件中定义 `declare-styleable`, 在java代码调用`set`方法来进行赋值的。

在 Databinding中，不在 `attrs` 文件中定义 `declare-styleable` ，也可以在xml文件中进行赋值，只需要对应的setter方法。

DataBinding 框架内置了几种调用 set 进行赋值的方式。

### Automatic Setters  
属性和set方法对应
```
<com.example.databindingsamples.view.NameCard
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:object="@{user}" />
```
```
public void setObject(User user){
    firstName.setText(user.getFirstName());
    lastName.setText(user.getLastName());
    age.setText(user.getAge());
}   
```
### Rename Attribute Setter
一些属性的命名与 setter 不对应。针对这些函数，可以用 BindingMethods 注解来将属性与 setter 绑定在一起。举个例子，`android:tint` 属性可以这样与 `setImageTintList(ColorStateList)`绑定，而不是 setTint: 
```java
@BindingMethods({
      @BindingMethod(type = "android.widget.ImageView",
                      attribute = "android:tint",
                      method = "setImageTintList"),
})
```
Android 框架中的 setter 重命名已经在库中实现了，我们只需要关注自己的 setter。

### Custom Attribute Setter
一些属性需要自定义 setter 逻辑。比如目前没有与`android:paddingLeft` 相对应的 setter，只有一个`setPadding(l,t,r,b)`函数。结合静态 binding adapter 函数与 BindingAdapter ，我们可以自定义属性 setter。
```
@BindingAdater("android:paddingLeft")
public static void setPaddingLeft(View view,int padding){
    view.setPadding(padding,
                    view.getPaddingTop(),
                    view.getPaddingRight(),
                    view.getPaddingBotton())
}
```
Binding adapter 在其他自定义类型上也很是 very nice 的。 For example，一个 loader 可以在非主线程加载图片。 当存在冲突时，开发者创建的 binding adapter 会覆盖 Data Binding 的默认adapter。
我们还可以创建多个 adapters 并且传递多个参数。
```
// 不需要主动调用
@BindingAdapter({"imageUrl,"error"}){
public static void loadImage(ImageView view, String url, Drawable error){
     Glide.with(context)
                .load(url)
                .error(error)
                .into(imageView); 
}
```
layout 文件的 ImageView 按照下面的写法，就会调用上面的adapter。
imageUrl 和 error 都使用，并且 imageUrl 为 String，error 为 drawable。
```xml
<ImageView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:imageUrl="@{url}"
    app:error="@{@drawable/ic_launcher}"
    />
```
> Tip： 
1. 在匹配adapter时，自定义命名空间将被忽略
2. 我们可以为 android 命名空间编写 adapter 


事件 handler 仅可以用于只有一个抽象方法的接口或者抽象类,比如
```java
@BindingAdapter("android:onLayoutChange")
public static void setOnLayoutChangeListener(View view, View.OnLayoutChangeListener oldValue,
       View.OnLayoutChangeListener newValue) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        if (oldValue != null) {
            view.removeOnLayoutChangeListener(oldValue);
        }
        if (newValue != null) {
            view.addOnLayoutChangeListener(newValue);
        }
    }
}
```
当一个 listener 有多个方法，它必须分割成多个 listener 。例如， View.OnAttachStateChangeListener 内置两个函数： `onViewAttachedToWindow()`与 `onViewDetachedFromWindow()` 。在这里必须为两个不同的属性创建不同的接口。
```java
@TargetApi(VERSION_CODES.HONEYCOMB_MR1)
public interface OnViewDetachedFromWindow {
    void onViewDetachedFromWindow(View v);
}

@TargetApi(VERSION_CODES.HONEYCOMB_MR1)
public interface OnViewAttachedToWindow {
    void onViewAttachedToWindow(View v);
}
```
因为改变一个 listener 会影响到另外一个，我们必须编写三个不同的 adapter，包括修改一个属性的和修改两个属性的。
```java
@BindingAdapter("android:onViewAttachedToWindow")
public static void setListener(View view, OnViewAttachedToWindow attached) {
    setListener(view, null, attached);
}

@BindingAdapter("android:onViewDetachedFromWindow")
public static void setListener(View view, OnViewDetachedFromWindow detached) {
    setListener(view, detached, null);
}

@BindingAdapter({"android:onViewDetachedFromWindow", "android:onViewAttachedToWindow"})
public static void setListener(View view, final OnViewDetachedFromWindow detach,
        final OnViewAttachedToWindow attach) {
    if (VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB_MR1) {
        final OnAttachStateChangeListener newListener;
        if (detach == null && attach == null) {
            newListener = null;
        } else {
            newListener = new OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(View v) {
                    if (attach != null) {
                        attach.onViewAttachedToWindow(v);
                    }
                }

                @Override
                public void onViewDetachedFromWindow(View v) {
                    if (detach != null) {
                        detach.onViewDetachedFromWindow(v);
                    }
                }
            };
        }
        final OnAttachStateChangeListener oldListener = ListenerUtil.trackListener(view,
                newListener, R.id.onAttachStateChangeListener);
        if (oldListener != null) {
            view.removeOnAttachStateChangeListener(oldListener);
        }
        if (newListener != null) {
            view.addOnAttachStateChangeListener(newListener);
        }
    }
}
```

上面的例子比普通情况下复杂，因为 View 是 `add/remove`　`View.OnAttachStateChangeListener` 而不是 `set`。 `android.databinding.adapters.ListenerUtil`可以用来辅助跟踪旧的 listener 并移除它。

对应 `addOnAttachStateChangeListener(View.OnAttachStateChangeListener) ) `支持的 api 版本，通过向 `OnViewDetachedFromWindow` 和 `OnViewAttachedToWindow` 添加 `@TargetApi(VERSION_CODES.HONEYCHOMB_MR1)` 注解，
Data Binding 代码生成器会知道这些 listener 只会在 Honeycomb MR1 或更新的设备上使用。

## Converters 
### Object Conversions
当 binding 表达式返回对象时，会选择一个 setter（自动 Setter，重命名 Setter，自定义 Setter），将返回对象强制转换成 setter 需要的类型。
下面是一个使用 ObservableMap 保存数据的例子：

```xml
<TextView
  android:text='@{userMap["lastName"]}'
  android:layout_width="wrap_content"
  android:layout_height="wrap_content"/>
```
在这里， userMap 会返回 Object 类型的值，而返回值会被自动转换成 setText(CharSequence) 需要的类型。当对参数类型存在疑惑时，开发者需要手动做类型转换。

### Custom Conversions 
有些时候我们需要自动转换成特定的类型。比如：
```xml
<View
   android:background="@{isError ? @color/red : @color/white}"
   android:layout_width="wrap_content"
   android:layout_height="wrap_content"/>
```
在这里，背景需要的是 Drawable ，但是 color 是一个整数。这时，我们需要使用 BindingConversation 来实现类型的转换。
```java
@BindingConversion
public static ColorDrawable convertColorToDrawable(int color) { 
    return new ColorDrawable(color); 
}
```

# Android Studio对Data Binding的支持

* Android Studio 支持 Data Binding 表现为：

    * 语法高亮
    * 标记表达式语法错误
    * XML 代码补全
    * 跳转到声明或快速文档 
    
> 注意：数组和泛型类型，如 Observable 类，当没有错误时可能会显示错误。

* 在预览窗口可显示 Data Binding 表达式的默认值。例如：

```xml
<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@{user.firstName, default=FirstName}"/>
  <!-- TextView 的 text 默认值为 FirstName -->
```

如果你需要在设计阶段显示默认值，你可以使用 `tools` 属性代替默认值表达式，详见 [设计阶段布局属性 ](http://tools.android.com/tips/layout-designtime-attributes)



  [1]: http://static.zybuluo.com/LeoPoldCrossing/eoib9q8z5vx6ijdish3fs09n/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202017-02-10%2010.04.22.png
  [2]: http://static.zybuluo.com/LeoPoldCrossing/we3idy2ri20tt8de1i4rj3am/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202017-02-13%2018.14.05.png