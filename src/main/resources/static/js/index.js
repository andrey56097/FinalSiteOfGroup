$(function() {


    $form         = $(".content-form");
    $input_name   = $("#user-name");
    $input_phone  = $("#user-phone");

    $form.on("submit", function(event){
      event.preventDefault();
      if (!$input_name.val()) {
        $input_name.siblings(".error-box").show();
      } else if (!$input_phone.val()) {
        $input_phone.siblings(".error-box").show();
      } else {
        document.location.href = "Home.html";
      }
    });
    


});

