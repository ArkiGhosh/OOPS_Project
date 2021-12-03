    <script>
        import { Router, navigate } from "svelte-navigator";
        import { userName } from "./store";
        import { Styles } from "sveltestrap";
        import {
            Collapse,
            Navbar,
            NavbarToggler,
            NavbarBrand,
            Nav,
            NavItem,
            NavLink,
            Dropdown,
            DropdownToggle,
            DropdownMenu,
            DropdownItem,
        } from "sveltestrap";
     
        let isOpen = false;
     
        function handleUpdate(event) {
            isOpen = event.detail.isOpen;
        }
        let fl = false;
        let fll = false;
        let arr = [
            { name: "6A", cost: "12$" },
            { name: "69A", cost: "12$" },
            { name: "67A", cost: "12$" },
        ];
        let brr = [false, false, false];
     
        console.log($userName);



        //Yo Dec 3 anubhav writing

        let loc;
        let date,intime,outtime



        async function search(e) {
            
            let arr = intime.split(':')
            console.log(arr[0])   

            //display all the slots

            //get all the bookings of that date
            //check if intime and outtime and slot clash with some booking, if yes color red
            //else color green

            //if red, add booking to waiting table instead
            //if a booking is cancled go through the booking table and if the slot is free in waiting table add it to booking table and remove from waiting
            //add booking to user collumn
            //make a user waiting column?

            
            


        }




        $:console.log(date)
        $:console.log(intime)


        




    </script>
     
    <div class = "hello">
    <Navbar color="dark" dark expand="md">
        <div class = "name">
        <NavbarBrand href="/">ParkMyCar <i class="fas fa-car-side" /></NavbarBrand>
        </div>
        <NavbarToggler on:click={() => (isOpen = !isOpen)} />
        <Collapse {isOpen} navbar expand="md" on:update={handleUpdate}>
          <Nav class="ms-auto" navbar>
            <div class="form-group" style="display:inline-block">
            <form class="navbar-form navbar-left" role="search">
                <div class="searchbar-container">
                <div class = "searchbar">
                <input type="text" class="form-control" placeholder="Search">
                </div>
                <div class = "searchicon">
                <button type="submit" class="btn_btn-default">
                    <span class="glyphicon glyphicon-search"><i class="fa fa-search"></i></span>
                </button>
                </div>
            </div>
            </form>
            </div>
            <NavItem>
              <NavLink href="https://github.com/an-bhv/carpark">GitHub</NavLink>
            </NavItem>
            <Dropdown nav inNavbar>
              <DropdownToggle nav caret>Options</DropdownToggle>
              <DropdownMenu end>
                <DropdownItem>Profile</DropdownItem>
                <DropdownItem>Log Out</DropdownItem>
              </DropdownMenu>
            </Dropdown>
          </Nav>
        </Collapse>
      </Navbar>
    </div>
     
     
    <br><br>
     
    <div class = "mybox">
    <h1>Hello, {$userName}!</h1>

        <label for="#">Location:</label>
        
        <select bind:value={loc}>
            <option value="A">
                A
            </option>
            <option value="B">
                B
            </option>
                                                                                  


        </select>
      
                  
        <label for="#">Date:</label>
        <input type="date"  bind:value="{date}"/>
        <div class = "hi">
        In Time: <input type="time" bind:value="{intime}"/>
        Out Time: <input type="time" bind:value="{outtime}" />
        </div>
        <br />
        <button class="search" on:click="{search}"><b>Search</b></button>
     
    </div>
   
    <style>
        * {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }
        .searchbar-container{
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            border-radius: 5px;
        }
        .box {
            width: 300px;
            border: 1px solid #333;
            border-radius: 2px;
            box-shadow: 2px 2px 8px rgba(0, 0, 0, 0.1);
            padding: 1em;
            margin: auto;
            margin-bottom: 1em;
            text-align: center;
            position: relative;
            background-color: lawngreen;
        }
     
        .search {
            border-color: #333 !important;
            color: lawngreen;
            background-color: #333;
        }
     
        span{
            color: lawngreen;
            background-color: #212529;
        }
     
        button{
            background-color: lawngreen !important;
            color: #333 !important;
            border-radius: 5px;
        }
     
        .mybox{
            background-color: #333 !important;
            padding: 40px;
            border-radius: 20px;
            display: flex;
            align-items: center;
            justify-content: center;
            align-self: center;
            width: 400px;
            flex-direction: column;
        }
     
        input{
            height: 5vh;
        }
     
        .searchicon{
            margin-left: 5px;
            margin-right: 5px;
            background-color: #212529;
        }
     
        .btn_btn-default{
            height: 30px;
            width: 30px;
            background-color: #212529 !important;
            border-color: #212529 !important;
        }
     
        .hello{
            display: inline-block;
            border-radius: 5px;
        }
     
        .name{
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            color: lawngreen;
        }
    </style>