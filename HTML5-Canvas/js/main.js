function setupPage() {
 setActive();
 showClock();
 setInterval(function() {
  showClock();
 }, 1000);
}

function setActive() {
 var
  url=document.URL,
  n=parseInt(url.substring(url.indexOf('?id=')+4, url.length))||-1,
  items=document.getElementById('nav').getElementsByTagName('li');

if(n<0 || n>items.length-1)
 return;

for(var i=0; i<items.length; i++)
 if(i===n)
  items[i].className='current'
 else
  items[i].className='';
}

function goHome() {
 location.href='index.html';
}

function showClock() {
 var
  date=new Date(),
  h=date.getHours(),
  min=date.getMinutes(),
  sec=date.getSeconds();
 if(h<10)
  h='0'+h;
 if(min<10)
  min='0'+min;
 if(sec<10)
  sec='0'+sec;
 document.getElementById('clock').textContent=h+':'+min+':'+sec;
}

function shareTo(service) {
 var
  url=document.URL,
  site,
  width=Math.floor(screen.width*0.55),
  height=Math.floor(screen.height*0.55),
  left=Math.floor((screen.width-width)/2),
  top=Math.floor((screen.height-height)/2);

 switch(service) {
  case 0:
   site='https://www.facebook.com/sharer/sharer.php?u=';
   break;
  case 1:
   site='http://twitter.com/share?url=';
   break;
  case 2:
   site='https://plus.google.com/share?url=';
   break;
  case 3:
   site='https://pinterest.com/pin/create/button/?url='
   break;
 }

 site=site+url;
 window.open(site, 'Podeli stranicu', 'height='+height+', width='+width+', toolbar=no, status=no, resizable=no, left='+left+', top='+top, false);
}