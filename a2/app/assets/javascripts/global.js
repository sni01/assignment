$(document).ready(function(){
   //set interval 10 seconds and render new messages
   var count = 0;
   window.setInterval(show_new_messages,10000);
   function show_new_messages(){
      $.ajax({
        type: "GET",
        url: "/",
        success: function(data){
		  //alert(data);
		  var message_item = $(data).find("div.message_item");
		  count = $("ul").find("li").length;
		  var length_of_data = message_item.length;
		  //alert(count);
		  var i = 0;
		  while(i < (length_of_data - count)){
		    var this_item_username = $(message_item[i]).find('.message_username').html();
			var this_item_content = $(message_item[i]).find('.message_content').html();
			var this_item_id = $(message_item[i]).find('.message_id').html();
			var this_item_app_id = $(message_item[i]).find('.message_app_id').html();
			var this_item_meta = $(message_item[i]).find('.message_created_time').html();
			//set timeago effect
			var clock = $(message_item[i]).find('.message_created_time').attr("title").split(" ");
			var date = clock[0];
	        var time = clock[1];
			var timeago_format = date+"T"+time+"Z";
			$(".messages_content").prepend($("<li><div class='message_item'><span class='message_username'>"+this_item_username+"</span><span class='message_content'>"+this_item_content+"</span><span class='message_id'>"+this_item_id+"</span><span class='message_app_id'>"+this_item_app_id+"</span><abbr class='message_created_time' title="+timeago_format+">"+this_item_meta+"</abbr></div></li>"));
			$("div.message_item").on('click',function(){
	              var item_id = $(this).find(".message_id").html();
		          var url = "messages/"+item_id;
		          window.location.href = url;
	        });
			i++;
		  }
		  $('abbr.message_created_time').timeago();
		},
		error: function(data){
		  console.log(arguments);
		}
    }); 
	}
});
$(document).ready(function(){
//timeago format
	$("abbr.message_created_time").each(function(){
       var clock = $(this).attr("title").split(" ");
       var date = clock[0];
	   var time = clock[1];
	   var timeago_format = date+"T"+time+"Z";
	   $(this).attr("title",timeago_format);
    });
    $("abbr.message_created_time").timeago();
	//show method trigger by click on messages
	$("div.message_item").on('click',function(){
	    var item_id = $(this).find(".message_id").html();
		var url = "messages/"+item_id;
		window.location.href = url;
	});

	//set back function from show page to main page
	$(".personal_message_back_root").on("click",function(){
	  window.location.href = "http://127.0.0.1:3000/";
	});
});