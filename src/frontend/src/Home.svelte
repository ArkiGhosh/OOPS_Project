

<script>
    let firstname, lastname, email;
    import { GoogleAuth} from '@beyonk/svelte-social-auth'
    import {Router, navigate} from "svelte-navigator"
    import {userName, userId} from './store'
    let username, password,role;
    const nav1 = () => {
        navigate("Signup");
    }
    const nav2 = () =>{
        navigate("Userdash");
    }
    const nav3 = () =>{
        navigate("Admin");
    }

    let data;
    function postlogin(e){
        data = e.detail.user
        firstname = data.vu.jf.split(" ")[0]
        lastname = data.vu.jf.split(" ")[1]
        email = data.vu.jv
        


        getUsersO();

    }

    let incorrect = false;

    async function getUsersO(){

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
            if(users[i]["email"] == email){

                userName.set(users[i]["username"])
                userId.set(users[i]["id"])
                nav2();

            }
           

        }
      
    }



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
            if(users[i]["username"]==username && users[i]["password"]==password){

                $userName = users[i]["username"]
                $userId  = users[i]["id"]
                nav2();
            }

        }
      
        incorrect = true;
    }

    async function getAdmin(){

        const res = await fetch("http://localhost:8080/admins",
        
            {
                method: "GET",
                headers: {
                    "Content-type": "application/json",
                    "Accept": "application/json",
                    "Access-Control-Allow-Origin": "http://localhost:8080",
                },
            }
        )


        const admins = await res.json()

        
        for (let i = 0;i<admins.length;i++){
            if(admins[i]["username"]==username && admins[i]["password"]==password){
                nav3();
            }

        }
      
        incorrect = true;
    }




    function login(){
        console.log("---")
        console.log(role)
        console.log("---")
        if(role=="User"){
           getUsers();


        }
        else if(role=="Admin"){
            getAdmin();
        }
        else{

        }

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
                <select name = "role" id = "role" bind:value="{role}">
                    <option value = "User">User</option>
                    <option value = "Admin">Administrator</option>
                    <option value = "Worker">Worker</option>
                </select>
            </div>
            <br><br>
            <div class = "submit-button">
            <button type = "submit" class = "submit-button" on:click="{login}"><b>Login</b></button>
            </div>
            <br>
            <GoogleAuth clientId="679316945176-9vuh27rfnk5g4fg71urgj85s89qfp91c.apps.googleusercontent.com" on:auth-success={e => postlogin(e)} />

                {#if incorrect==true}
                    <p style="color:red">Incorrect Username or Password</p>
                {/if}

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