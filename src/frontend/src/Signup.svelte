   
 <script>
        import { Router, navigate } from "svelte-navigator";
        let flag = false;
        let user_email;
        let otp;
        let user_otp;

        let userNameTaken = false;
        
        let password = "", cpassword = "",fn="",ln="",un="",ad="",em="",nm="",cr="";



        
        async function getUsers(){

        const res = await fetch("http://localhost:8080/users",
        
            {
                method: "GET",
                headers: {
                    "Content-type": "application/json",
                    "Accept": "application/json",
                    "Access-Control-Allow-Origin": "http://localhost:8080",
                },
            }
        )


        const users = await res.json()

        
        for (let i = 0;i<users.length;i++){
            if(users[i]["username"]==un){
                userNameTaken = true;
                return;
            }

        }
        userNameTaken = false;

    }

    $:{
        getUsers();
        console.log(un);
    }




        function onSubmit(e) {
            const formData = new FormData(e.target);
            flag = true;
            const data = {};
            for (let field of formData) {
                const [key, value] = field;
                data[key] = value;
            }
            data["id"] = "3";
            data["active"] = false;
            console.log(data);
            fetch("http://localhost:8080/add_user", {
                method: "POST",
                headers: {
                    "Content-type": "application/json",
                    "Cache-Control": "no-cache",
                    'Accept': "application/json",
                    "Access-Control-Allow-Origin": "http://localhost:8080",
                },
                body: JSON.stringify(data),
            })
                .then((res) => {
                    res.json();
                })
                .then((data) => {
                    console.log(data);
                })
                .catch((err) => {
                    console.log(err.message);
                });
        }
        
            async function otp_generate() {
            console.log(user_email);
     
            const s = "http://localhost:8080/otp/" + user_email;
            const data = user_email;
            const res = await fetch(s, {
                method: "GET",
                headers: {
                    "Content-type": "application/json",
                    Accept: "application/json",
                    "Access-Control-Allow-Origin": "http://localhost:8080",
                },
            });
                const text = await res.json();
                console.log(text);
                otp = text;
                return text;
     
            
        }
        
        async function verify(e) {

            console.log(otp["otp"]);
            console.log(user_otp);

            if (otp["otp"] == user_otp){
                const s = "http://localhost:8080/setact/" + user_email;
                const data = user_email;
                const res = await fetch(s, {
                method: "GET",
                headers: {
                    "Content-type": "application/json",
                    Accept: "application/json",
                    "Access-Control-Allow-Origin": "http://localhost:8080",
                },
                    });

            
                
                var msg = document.createElement("P");
                msg.innerHTML = "Registered successfully";
                document.getElementById("verification").appendChild(msg);
            }
            else{
                var msg = document.createElement("P");
                msg.innerHTML = "Incorrect OTP, please try again";
                document.getElementById("verification").appendChild(msg);
            }
        }
    </script>
     
    {#if flag == false}
        <form on:submit|preventDefault={onSubmit}>
            <div class="login">
                <br /><br /><br /><br /><br /><br />
                <div class="title">
                    <h1>ParkMyCar <i class="fas fa-car-side" /></h1>
                </div>
     
                <br /><br />
                <div class="format">
                    <div class="box">
                        <div>
                            First
                            name:&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;
                            <input type="text" name="firstname" class="firstname" bind:value="{fn}"/>
                        </div>
                        <br />
     
                        <div>
                            Last
                            name:&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;
                            <input type="text" name="lastname" class="lastname" bind:value="{ln}"/>
                        </div>
                        <br />
     
                        <div>
                            Username:&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;
                            <input type="text" name="username" class="username" bind:value="{un}"/>
                        </div>
                        <br />
     
                        <div>
                            Password:&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                            <input
                                type="password"
                                name="password"
                                class="password"
                                bind:value="{password}"
                            />
                        </div>
                        <br />
     
                        <div>
                            Confirm password:&emsp;&emsp;&emsp;&ensp;
                            <input
                                type="password"
                                name="cpassword"
                                class="cpassword"
                                bind:value="{cpassword}"
                            />
                        </div>
                        <br />
     
                        <div>
                            Residential Address: &emsp;&emsp;&ensp;
                            <input type="text" name="address" class="address" bind:value="{ad}"/>
                        </div>
                        <br />
     
                        <div>
                            Email ID:
                            &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;
                            <input type="text" name="email" class="email" bind:value = {user_email}>
                        </div>
                        <br />
     
                        <div>
                            Mobile number: &nbsp;&emsp;&emsp;&emsp;&emsp;&nbsp;
                            <input type="text" name="number" class="number" bind:value="{nm}"/>
                        </div>
                        <br />
     
                        <div>
                            Car Registration Number:
                            <input type="text" name="crn" class="crn" bind:value="{cr}"/>
                        </div>
     
                        <br /><br />

                        {#if password!=cpassword}
                            <p>Type the same password</p>

                        {:else if userNameTaken==true}
                            <p style="color: red;">Username has already been taken</p>
                        {:else}
                        <div class="submit-button">
                            <button class="submit-button"><b>Sign up</b></button>
                        </div>
                        <br />

                        {/if}
     
                        <pre>Have an account? Click to <a href = "http://localhost:5000/">Login!</a></pre>
                    </div>
                </div>
            </div>
        </form>
    {/if}
     
    {#if flag == true}
        <br /><br />
        <form on:submit|preventDefault={otp_generate} class="otpgen" >
     
            <div class="generate">
                <button type="submit" class="submit-to-generate">
                    <h2><b>Generate OTP</b></h2>
                </button>
            </div>
        </form>
     
        <form on:submit|preventDefault={verify} class="box">
            <div class="formotp">
                <h1>
                    Please enter the OTP sent to your registered email ID to
                    complete the verification process:
                </h1>
                <br />
                <div class="box">
                    <div class="otp_input">
                        <input type="text" name="otp_input" bind:value={user_otp}/>
                    </div>
                    <br />
                    <div id="verification"></div>
                    <br />
                    <div class="submitotp">
                        <button type="submit" class="submito"><b>Verify</b></button>
                    </div>
                </div>
            </div>
        </form>
    {/if}
     
    <style>
        input {
            margin: 5px;
            text-align: center;
        }
     
        * {
            padding: 0%;
            margin: 0;
            box-sizing: border-box;
        }
     
        .login {
            display: flex;
            align-items: center;
            justify-content: space-around;
            height: 100vh;
            flex-direction: column;
        }
     
        .box {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }
     
        h1 {
            text-align: center;
        }
     
        .title {
            font-size: 45px;
            color: #333;
        }
     
        .format {
            background-color: #333 !important;
            padding: 40px;
            border-radius: 20px;
        }
     
        button {
            background-color: lawngreen;
            border-color: #333;
            padding: 5px;
        }
     
        .submit-button {
            width: 206px;
        }
     
        a {
            color: blueviolet;
        }
        .submit-to-generate {
            width: 200px;
            height: 80px;
            background-color: #333;
            border-color: lawngreen !important;
            border-radius: 10px;
            color: lawngreen;
            padding: 5px;
            margin-top: 8em;
        }
     
        .otpgen {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }
        .formotp {
            color: #333;
            padding: 40px;
            width: 1000px;
        }
     
        .submito {
            border-color: lawngreen;
            background-color: #333;
            color: lawngreen;
            width: 206px;
        }
     
        .verification{
            color: #333;
        }
    </style>