[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-progress--activity-green.svg?style=flat)](https://android-arsenal.com/details/1/2386)

# progress-activity

If you are tired of constantly having to set up progress bars, empty views and error views, than this library is for you.

### About

Using progress-activity which extends from RelativeLayout, you can easily add states like

- progress bar while the content is loading,
- empty view to indicate when there are no data to display,
- error view to indicate when something goes wrong with a button to try again.

### Screenshots

<img src="http://i.imgur.com/oEsvKDc.png" width="250">
<img src="http://i.imgur.com/VLqt4tS.png" width="250">
<img src="http://i.imgur.com/zJY7WIS.png" width="250">

### Features

- show/hide progress bar,
- show/hide empty view,
- show/hide error view,
- show content when the data is ready to be displayed,

### Usage

Add ```com.vlonjatg.progressactivity.ProgressActivity``` to your layout.

```xml
<com.vlonjatg.progressactivity.ProgressActivity
	android:id="@+id/progressActivity"
    android:layout_width="match_parent"
	android:layout_height="match_parent"
    android:layout_below="@+id/activityToolbar"
    progressActivity:loadingBackgroundColor="#FFFFFF"
    progressActivity:emptyBackgroundColor="#fbc02d"
    progressActivity:errorBackgroundColor="#42a5f5">
    
        <TextView
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:layout_centerInParent="true"
            android:gravity="center"
            android:text="@string/hello_world" />

    </com.vlonjatg.progressactivity.ProgressActivity>
```

Then in your code:

To display the loading view:

```java
progressActivity.showLoading();
```

To display the empty view:

```java
progressActivity.showEmpty(emptyDrawable, "Empty Shopping Cart", 
		"Please add things in the cart to continue.");
``` 

To display the error view:

```java
progressActivity.showError(errorDrawable, "No Connection",
		"We could not establish a connection with our servers. Try again when you are connected to the interne.",
        "Try Again", errorClickListener);
``` 

To show the content, use:

```java
progressActivity.showContent()
```

### Get It

````groovy
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
        compile 'com.github.vlonjatg:progress-activity:v1.0.3'
}
```

### Customization

There are a bunch of attributes to customize the views.

```xml
<attr name="loadingProgressBarWidth" format="dimension"/>
<attr name="loadingProgressBarHeight" format="dimension"/>
<attr name="loadingBackgroundColor" format="color"/>

<attr name="emptyImageWidth" format="dimension"/>
<attr name="emptyImageHeight" format="dimension"/>
<attr name="emptyTitleTextSize" format="dimension"/>
<attr name="emptyContentTextSize" format="dimension"/>
<attr name="emptyTitleTextColor" format="color"/>
<attr name="emptyContentTextColor" format="color"/>
<attr name="emptyBackgroundColor" format="color"/>

<attr name="errorImageWidth" format="dimension"/>
<attr name="errorImageHeight" format="dimension"/>
<attr name="errorTitleTextSize" format="dimension"/>
<attr name="errorContentTextSize" format="dimension"/>
<attr name="errorTitleTextColor" format="color"/>
<attr name="errorContentTextColor" format="color"/>
<attr name="errorButtonTextColor" format="color"/>
<attr name="errorBackgroundColor" format="color"/>
```

### Example

An [example](https://github.com/vlonjatg/progress-activity/tree/master/sample) is available.

### Developed By

Vlonjat Gashi - [Twitter](https://twitter.com/vlonjatg)

### Attributes

This library is inspired by [AndroidProgressLayout](https://github.com/antonkrasov/AndroidProgressLayout) library from Anton Krasov.

### License

progress-activity is available under the [MIT](http://opensource.org/licenses/MIT) licence.

```
The MIT License (MIT)

Copyright (c) 2015 Vlonjat Gashi

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
```
