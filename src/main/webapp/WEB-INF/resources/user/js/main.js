function fmoney(s, n) {
	n = n > 0 && n <= 20 ? n : 2;
	s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
	t = "";
	for (i = 0; i < l.length; i++) {
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	}
	return t.split("").reverse().join("") + "." + r;
}

function checkInputIntFloat(oInput) {
	if ('' != oInput.value.replace(/\d{1,}\.{0,1}\d{0,}/, '')) {
		oInput.value = oInput.value.match(/\d{1,}\.{0,1}\d{0,}/) == null ? ''
				: oInput.value.match(/\d{1,}\.{0,1}\d{0,}/);
	}
}

function verifyIdCard(idCard) {
	var re = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
	if (re.test(idCard)) {
		return true;
	} else {
		return false;
	}
}

function verifyPhone(phone) {
	var re = /^1\d{10}$/;
	if (re.test(phone)) {
		return true;
	} else {
		return false;
	}
}