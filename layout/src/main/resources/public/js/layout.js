$(document).ready(function() {
	// 显示或者隐藏侧边栏
	//菜单的
  $('[data-toggle="sidebar"]').click(function() {
    $('.sidebar').toggleClass('active');
  });
  //用ajax获得当前的菜单，并显示出来
  $.ajax({
	  url: contextPath + "/menu/menus",
	  method: "GET",
	  dataType: "JSON",
	  success: function(menus, status, xhr){
	  
		/*<!-- 一级、二级菜单显示的地方 -->
		<!-- <div class="col-sm-3 col-md-2 sidebar">
			<div class="sidebar-header">一级菜单</div>
			<ul class="nav nav-sidebar ">
				<li class="active">
					<a href="index.html#">
						二级菜单 <span class="sr-only">(current)</span>
					</a>
				</li>
				<li><a href="index.html#">Reports</a></li>
				<li><a href="index.html#">Analytics</a></li>
				<li><a href="index.html#">Export</a></li>
				
				<li><a href="index.html#">二级菜单</a></li>
				<li><a href="index.html#">二级菜单</a></li>
				<li><a href="index.html#">二级菜单</a></li>
			</ul>
		</div> -->*/
			
		  //显示菜单
		  for(var i = 0; i < menus.length; i++){
			  	// 使用反引号是ECMAScript 5/6里面提供的新功能，可以直接写多行的字符串
				// $表示引用变量，后面跟着前面定义的变量名		
				// ${menu.name}不能在JSP使用，只能在JS文件中使用
			  //显示一级菜单
			  var menu = menus[i];
			  var html = `<div class="sidebar-header">${menu.name}</div>
			  <ul class="nav nav-sidebar">`;
			  
			  //显示二级菜单
			// <li><a href="index.html#">二级菜单</a></li>
			  for(var j = 0 ; j < menu.children.length; j++){
				  var item = `<li><a href="${menu.children[j].url}">${menu.children[j].name}</a></li>`;
				  html += item;
			  }
			  
			  html += "</ul>";
			  
			  if(menu.children.length > 0){
				  $(html).appendTo($("#left-sidebar"));
			  }
		  }
	  },
	  error: function(data, status,xhr){
		  //弹出错误信息
		  alert(data.responseJSON.message);
	  }
  });
});