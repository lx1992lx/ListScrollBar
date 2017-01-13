# ListScrollBar
 ![image](https://github.com/lx1992lx/ListScrollBar/raw/master/通信录导航条.gif)
 
 使用方法
 -------
 复制ListScrollBar类到你的项目中，在xml布局中创建ListScrollBar。<br>
 
     <com.yyxk.listscrollbar.ListScrollBar
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:id="@+id/list"/>
            
 在Java代码中，调用setData()方法传入数据。在与ListView联动时，可以使用setUpWithListView或者使用setOnScrollListener接口监听滚动。其中setUpWithListView需要配合带有SelectionHeader的ListView。
