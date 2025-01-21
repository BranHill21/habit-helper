document.getElementById("loginForm").addEventListener("submit", async (e) => {
	e.preventDefault(); // Prevent the default form submission

	const loginData = {
		username: document.getElementById("inputUsername").value,
		password: document.getElementById("inputPassword").value,
	};
	console.log(loginData);

	try {
		const response = await fetch("http://localhost:8080/login", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify(loginData),
		});

		if (response.ok) {
			const data = await response.json();
			alert(`Login successful! Token: ${data.token}`);
		} else {
			const error = await response.text();
			alert(`Login failed: ${error}`);
		}
	} catch (err) {
		console.error("Error during login:", err);
		alert("An error occurred while trying to log in. Please try again.");
	}
});