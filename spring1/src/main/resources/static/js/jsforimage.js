/**
 * image.html
 */
 

 // let temp = [1, 2, 3, "apple", [3.14, 45.34]];
 // console.log(temp[4][1]);
        
//1초가 지날 때마다, 이미지가 변한다.(소스명이 바뀜)
window.onload = function (){
	let imgary =['img/mokoko01.jpg', 'img/mokoko02.jpg', 'img/mokoko03.jpg', 'img/mokoko04.jpg', 
                 'img/mokoko05.png', 'img/mokoko06.png', 'img/mokoko07.png', 'img/mokoko08.png'];
    let btn = document.getElementById("btn");
    
	let i = 1;
	//setInterval은 그 자체로 반복문의 성격을 띈다.
	let interval = setInterval(function(){
						currImg = imgary[i];
						document.getElementById("myImg").setAttribute("src", currImg);
						++i;
					if(i == imgary.length){
						i = 0;
					}}, 1000);
	
	btn.onclick = function(){
		clearInterval(interval);
	} 
}
