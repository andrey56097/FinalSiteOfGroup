
    $(function(){
        
        $('#f').submit(function(){
        
            var urlFull = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&key=ABQIAAAACKQaiZJrS0bhr9YARgDqUxQBCBLUIYB7IF2WaNrkYqF0tBovNBQFDtM_KNtb3xQxWff2mI5hipc3lg&rsz=large&q="+ encodeURIComponent(jQuery("#q").val()) + "&callback=GoogleCallback&context=?";
            
            $.ajax({
               url: urlFull,
               dataType : "jsonp",
               
               
               // Handle the success event
               success: function (data, textStatus) {
    				var ol = document.createElement("ol");
                    // build results set
                    $.each(data.results,
        					function(i, val) {
        						var li = document.createElement("li");
        						
        						li.innerHTML = '<a href="'+val.url+'" title="'+val.url+'" target="_blank">'+val.title+"</a> - "+val.content;        						
        						
                                ol.appendChild(li);
        					}
        				);
    				$('#results').html(ol);
    		    }				
    		});
            return false;
        });
        
    });
    
    function GoogleCallback (func, data) {
        window[func](data);
    }
