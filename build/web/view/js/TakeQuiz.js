function setTime(timeEnd){
    //Update the count down every 1 second
    var x = setInterval(function (){
        // Get datetime now
        var now = new Date().getTime();
        // Find the distance between now and the count down date
        var distance = timeEnd - now;
        // Time calculations for days, hours, minutes and seconds
        var minutes = Math.floor((distance % (1000 * 60 * 60))/ (1000*60));
        var seconds = Math.floor((distance % (1000*60))/1000);
        
        if(seconds<10){
            seconds = "0"+seconds;
        }
        if(minutes<10){
            minutes = "0"+minutes;
        }
        // Output the time
        document.getElementById("time").innerHTML = minutes + ":"+seconds;
        //time over
        if(distance<0){
            clearInterval();
            document.getElementById("form").submit();
        }
        
    }, 1000);
}

function handleOptionClick(param) {
    if (!param.children[0].checked) {
        param.children[0].checked = true;
        param.classList.add("selected");
    } else {
        param.children[0].checked = false;
        param.classList.remove("selected");
    }
}