package com.kun98.encrypt.rsa;

/**
 * rsa加解密用的公钥和私钥
 */
public class RsaKeys {

	//生成秘钥对的方法可以参考这篇帖子
	//https://www.cnblogs.com/yucy/p/8962823.html

	//服务器公钥
	private static final String serverPubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzSJK+Pc1IdFWz83FWvKH" +
			"kTLmHq87J+5ndEdlH86c9AcSlaj4hyTWyaQOP8WPuvDfxL93TbYUEQ8bidHwyVAI" +
			"THoDABDQqBr0mDTfTqogHxWaqjEUh+g+y96KfshmD312lZD3cMxSrgA1NUlTBMpT" +
			"JQ+GQ1Rn0qVaVAi6OziAct9HEeFupJiw09sLzQTt5zY5s/KOgjQ7wPck4pGxO3tl" +
			"p/Iwws7WyabOjPm1z7XxcHWsetO9H6nKeJ8WaZ6P6baFasiNuhiBOvnGslRJ0Dgd" +
			"xsz7+feK3JbIwHr6vVfhJ9Bn2rnSYqpydg3odMyU54/lV0kdWup7SbJILAtaS8kv" +
			"EwIDAQAB";

	//服务器私钥(经过pkcs8格式处理)
	private static final String serverPrvKeyPkcs8 = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDNIkr49zUh0VbP" +
			"zcVa8oeRMuYerzsn7md0R2Ufzpz0BxKVqPiHJNbJpA4/xY+68N/Ev3dNthQRDxuJ" +
			"0fDJUAhMegMAENCoGvSYNN9OqiAfFZqqMRSH6D7L3op+yGYPfXaVkPdwzFKuADU1" +
			"SVMEylMlD4ZDVGfSpVpUCLo7OIBy30cR4W6kmLDT2wvNBO3nNjmz8o6CNDvA9yTi" +
			"kbE7e2Wn8jDCztbJps6M+bXPtfFwdax6070fqcp4nxZpno/ptoVqyI26GIE6+cay" +
			"VEnQOB3GzPv594rclsjAevq9V+En0GfaudJiqnJ2Deh0zJTnj+VXSR1a6ntJskgs" +
			"C1pLyS8TAgMBAAECggEATsFQuV7ndjFRu/xLPcyJQbSh5rvt4TnFXD4g0+JWHdYt" +
			"S2oQ6Im7MLUch11I/kOGFZpQqnQyJg5/yxzf7dodJYdeaYMLKM8YgushpKjqJiT8" +
			"OUUYlckTet/Ymi5ECMRpZ6i9Zv/66jTIOMoK5nbrDvpz2JuqNJQpJsQnA5+AqRjh" +
			"r3gq1wcwME3zFiC7BCtsSxKO6CHISHTov4LxgGOsmfinHP5lHoZxKnmal23OXahP" +
			"oWAuojVJ4y24hJTm1YAst32Zo4Hh4ivRebYGdfyjoOU+9HR957iF4W+bGb2zs4s5" +
			"FeY+pen2z+U4XXted4T+eG6dESJz2uuoG98oL58KqQKBgQDuMvIhQojSvpxSa37w" +
			"IwobDNS1tUFyccJitC44ys71H8EiFYHMuRZTdEeuV8gAWmGN7KrFn0EIQnXwWk1J" +
			"qwgKDbRdbxToxSTnKnFcMvwO0YmmAubpgf9ppctzirlNcLhDdPYUCAnMjyuN6W2N" +
			"pygyJTKMKlluGeocQ7mqaNpAnwKBgQDcdsUdodsMsop/6ishuVJmzh3rFlcNNh/B" +
			"nglGCuWCIJUU7XyNj3mfN3m+SHtZRKaEWA3XcBcTtFFsHkJvjy6TiqMOrOrwVjEj" +
			"j+i0nd1Jfx5IKU/pcBiFKtkTQYwi6IXOc9Ui6CC/XB/V3LLQzyDA4tvQjHpAKC/B" +
			"71jRm1+5DQKBgQDV5QWsfDSlGekB0emg9bYDaFgx+1uTyzeErsu6z9NcJnGli66N" +
			"Cb9UnVwo4EaGmqJzcYw//avGIPgLJuu0NVL4xCmspS0fgLiMpH47DsVtARgb7Qsx" +
			"sDyMwAab8HxJX+j3GjZG8pjqCb5QpsZrpyjfLqvfVcAMsFSboO0+av1hfQKBgQCi" +
			"OGacgj9rXWiZ7NWl7/ZZHStYk0yktQGy9zV9q4DrOkxYZNM0WrE0XZ6gTDcvHVul" +
			"oCE3OAxS/Gi5NJ7P9bxg5i9LGiOZiuKHd1nUpSBx9y5yDKv3afsw6bFnAOE7wnrK" +
			"yeK317RY+lGWjNmq2e4Q4By8nNFLqgZSHmrtePV7dQKBgQCq06PA6c8mdIz5tB1w" +
			"tmpBJlpGC7lggfjDT+Lf362ZC3lu/iyIcGp4qNpKzJvUGhFGh1NQ1QniVoY9boC9" +
			"MK3XoxbiyA8D+j2fk8bFX+ZKhMypm0JLJshAW6wcPay0/8qbVzFRKcIqCU2e1+Qd" +
			"2doDo3HCqW68aiANvUOhfhTRCg==";

	public static String getServerPubKey() {
		return serverPubKey;
	}

	public static String getServerPrvKeyPkcs8() {
		return serverPrvKeyPkcs8;
	}
	
}
