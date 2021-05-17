var i = 0;
var text = "“The minute I heard my first love story, I started looking for you. ”";
var speed = 50;



function typeWriter(){
    if (i < text.length) {
        document.getElementById("typing_effect").innerHTML += text.charAt(i);
        i++;
        setTimeout(typeWriter, speed);
      }
    }


