let websocket = new WebSocket("ws://localhost:1331");

websocket.onopen = function(e) {
}

websocket.onmessage = function(e) {
	console.log(e.data);

	let json;
   	try {
   		json = JSON.parse(e.data);
  	} catch (e) {
    	return;
   	}

   	let type = json.type;
   	if (type == "Login") {
   		let message = json.message;
   		if (message == "password error") {
   			sendPopup("info", "<img style='width: 30px' src='image/error.svg'>密码错误！", 3000);
   		}
   		if (message == "login success") {
   			// sendPopup("info", "<img style='width: 30px' src='image/bingo.svg'>登录成功！", 3000);
			document.querySelector(".loading").style.display = "block";
   		}
   	}
}

websocket.onclose = function(e) {
}

websocket.onerror = function(e) {
}