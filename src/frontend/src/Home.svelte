

<script>
    let firstname, lastname, email;
    import { GoogleAuth} from '@beyonk/svelte-social-auth'
    import {Router, navigate} from "svelte-navigator";
    let username, password;
    const nav1 = () => {
        navigate("Signup");
    }
    const nav2 = () =>{
        navigate("Userdash");
    }

    let data;
    function postlogin(e){
        data = e.detail.user
        firstname = data.vu.jf.split(" ")[0]
        lastname = data.vu.jf.split(" ")[1]
        email = data.vu.jv
        console.log(firstname)
        console.log(lastname)
        console.log(email)

    }
    
</script>


<p align = "right"><button class = "sub" on:click = {nav1}>Sign Up</button>
    <button class="sub" on:click={nav2}>User</button>
</p>

<div class = "login">
    
    <div class = "title">
        <h1>ParkMyCar <i class="fas fa-car-side"></i>
        </h1>
        
    </div>

    <div class = "format">
        <h1>Login</h1>
        <br>
        <div class = "box">
            <div class = "user">
            <label for="username">Username</label>
            <input type="text" name="username" bind:value={username}>
            </div>
            <div class = "user">
            <label for="password">Password</label>
            <input type="password" name="password" bind:value={password}>
            </div>
            <div class = "role">
                <label for = "role">Choose your role:</label>
                <select name = "role" id = "role">
                    <option value = "User">User</option>
                    <option value = "Admin">Administrator</option>
                    <option value = "Employee">Employee</option>
                </select>
            </div>
            <br><br>
            <div class = "submit-button">
            <button type = "submit" class = "submit-button"><b>Login</b></button>
            </div>
            <br>
            <GoogleAuth clientId="679316945176-9vuh27rfnk5g4fg71urgj85s89qfp91c.apps.googleusercontent.com" on:auth-success={e => postlogin(e)} />
        </div>
    </div>
</div>

<style>
    label,input{
        margin: 5px;
        text-align: center;
    }
    *{
        padding:0%;
        margin: 0;
        box-sizing: border-box;

    }
 
    .login{
        display: flex;
        align-items: center;
        justify-content: space-around;
        height: 100vh;
        flex-direction: column;
    }

    .box{
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
    }

    h1{
        text-align: center;
    }

    .title{
        font-size: 45px;
        color: #333;
    }
    
    .format{
        background-color: #333 !important;
        padding: 40px;
        border-radius: 20px;
    }

    button{
        background-color: lawngreen;
        border-color: #333;
        padding: 5px;
    }

    .submit-button{
        width: 206px;
    }

    .sub{
        background-color: #333;
        color:lawngreen;
        padding: 10px;
        margin-top: 10px;
        margin-right: 10px;
    }
</style>