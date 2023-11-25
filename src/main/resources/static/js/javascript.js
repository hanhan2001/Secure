
let popup = null;

function login() {
	let user = document.querySelector(".content .box .login .user");
	let pass = document.querySelector(".content .box .login .pass");

	if (user.value == false) {
		sendPopup("info", "<img style='width: 30px' src='image/error.svg'>账户不可为空", 2000);
		return;
	}

	if (pass.value == false) {
		sendPopup("info", "<img style='width: 30px' src='image/error.svg'>密码不可为空", 2000);
		return;
	}

	websocket.send("{\n\"type\":\"Login\",\n" + 
		"\"user\":\"" + user.value + "\",\n" + 
		"\"pass\":\"" + pass.value + "\"\n}");
}

/**
 * 显示弹窗信息
 * 
 * @param type 弹窗类型
 * @param message 消息
 * @param ms 显示毫秒
 * */
function sendPopup(type, message, ms) {
	let box;
	if (type == "info") {
		box = document.querySelector(".popup .info");
		if (popup != null)
			return;
		box.style.display = "flex";
		content = document.querySelector(".popup .info lable");
		content.innerHTML = message;
		// box.classList.add("topto");

		popup = setTimeout(() => {
			// box.classList.remove("topto");
			box.style.display = "none";
			popup = null;
			clearTimeout(popup);
		}, ms);
	}
}