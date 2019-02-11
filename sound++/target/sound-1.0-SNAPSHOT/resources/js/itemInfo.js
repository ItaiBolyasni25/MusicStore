  $(document).ready(function(){
            $("tr").each(function(){
                $(this).click(function(){
                    $("#info").css('display', 'block');
                    $("#albumTable").css('display', 'none');
                    console.log($(this).attr('id'));
            });});});