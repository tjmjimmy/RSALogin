/**
 * 登录处理js
 */
$(function(){
	
})

function ajaxLogin(){
	var username = $("#username").val();
	var password = $("#password").val();
	
	var modulus = $("#hidden_modulus").val();
	var exponent = $("#hidden_exponent").val();
	
	var key = RSAUtils.getKeyPair(exponent, '', modulus);
	encryptedpwd = RSAUtils.encryptedString(key, password);
	
	$.ajax({
		url: "/RSALogin/ajaxLogin",
		method: 'post',
		data: {'username': username, 'password': encryptedpwd},
		dataType: 'json',
		success: function(result){
			if(result.code == "200"){
				alert("登录成功！");
			}
		}
	})
}