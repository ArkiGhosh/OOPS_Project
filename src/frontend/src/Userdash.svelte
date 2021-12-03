    <script>
        import { Router, navigate } from "svelte-navigator";
        import { Form, FormGroup, FormText, Input, Label } from "sveltestrap";
        import { userName } from "./store";
        import { Styles } from "sveltestrap";
        import { Col, Container, Row } from "sveltestrap";
        import {
            Button,
            Card,
            CardBody,
            CardFooter,
            CardHeader,
            CardSubtitle,
            CardText,
            CardTitle,
        } from "sveltestrap";
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
import { space } from "svelte/internal";
     
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
            { name: "68A", cost: "12$" },
            { name: "70A", cost: "12$" },
        ];
        var brr = {};
        var frr = {};
    
        //Dec 3


        let loc;
        let date,intime,outtime
        let cost

        $: cost = 25*(outtime-intime)

          async function search(e) {
         
            //display all the slots
            //get all the bookings of that date
            //check if intime and outtime and slot clash with some booking, if yes color red
            //else color green
            //if red, add booking to waiting table instead
            //if a booking is cancled go through the booking table and if the slot is free in waiting table add it to booking table and remove from waiting
            //add booking to user collumn
            //make a user waiting column?

            let s = "http://localhost:8080/slots";

            const res = await fetch(s, {
                method: "GET",
                headers: {
                    "Content-type": "application/json",
                    Accept: "application/json",
                    "Access-Control-Allow-Origin": "http://localhost:8080",
                },
                    });

            const slots = await res.json()
            console.log(date)
            
            s = "http://localhost:8080/bookings/space/"+loc+"/date/"+date


            const res2 = await fetch(s, {
                method: "GET",
                headers: {
                    "Content-type": "application/json",
                    Accept: "application/json",
                    "Access-Control-Allow-Origin": "http://localhost:8080",
                },
                    });


            let bookings = await res2.json()


            arr = slots
     
                    

            for(let i =0;i<arr.length;i++){
                brr[arr[i].slotnum] = "success";
                frr[arr[i].slotnum] = "1hr";
            }

            console.log(bookings)

            for(let i= 0;i<bookings.length;i++){
                    let boo = bookings[i];
                    console.log("---------------")
                    console.log(boo)
                    console.log(boo.outtime)
                    console.log(intime)
                    console.log(boo.intime)
                    console.log(outtime)
                    console.log(boo.slotid)
                    console.log("---------------")
                if(brr[boo.slotid]=="success"){
                    console.log("here")
                    if(!((boo.outtime<(intime))||((outtime)<(boo.intime)))){
                        brr[boo.slotid]="danger";
                        frr[boo.slotid]=boo.outtime-intime;
                        if(boo.outtime==intime) brr[boo.slotid] = "success"
                    }
                }                

            }
            
            
            console.log(brr)

        }




















        console.log($userName);
    </script>
     
    <div class="hello">
        <Navbar class="rounded-3" color="dark" dark expand="md">
            <div class="name">
                <NavbarBrand href="/"
                    >ParkMyCar <i class="fas fa-car-side" /></NavbarBrand
                >
            </div>
            <NavbarToggler on:click={() => (isOpen = !isOpen)} />
            <Collapse {isOpen} navbar expand="md" on:update={handleUpdate}>
                <Nav class="ms-auto" navbar>
                    <div class="form-group" style="display:inline-block">
                        <form class="navbar-form navbar-left" role="search">
                            <div class="searchbar-container">
                                <div class="searchbar">
                                    <input
                                        type="text"
                                        class="form-control"
                                        placeholder="Search"
                                    />
                                </div>
                                <div class="searchicon">
                                    <button type="submit" class="btn_btn-default">
                                        <span class="glyphicon glyphicon-search"
                                            ><i class="fa fa-search" /></span
                                        >
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <NavItem>
                        <NavLink href="https://github.com/an-bhv/carpark"
                            >GitHub</NavLink
                        >
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
     
    <br /><br />
     
    <div class="mybox">
        <h1>Hello, {$userName}!</h1>
        <form
            on:submit|preventDefault={function () {
                fl = true;
            }}
        >
        <FormGroup>
            <Label for="#">Location:</Label>
        
        <Input type="select" bind:value={loc}>
            <option value="A" selected>
                A
            </option>
            <option value="B">
                B
            </option>
                                                                                  


        </Input>
        </FormGroup>
        <FormGroup>
            <Label for="#">Date:</Label>
            <Input type="date" required  bind:value="{date}"/>
        </FormGroup>
        <FormGroup>
        <Container>
        <Row cols = {2}>
            <div class="" style = "display:inline-block">
            <Col>
            <Card style = "background-color:#333;border-color:#333;width:8rem">
            <Label for="#">In Time:</Label>
            <Input type="select" style = "width: 6rem;" bind:value="{intime}">

                {#each Array(24)  as _,i}
                    <option value="{i}">{i}</option>
                {/each}
            </Input>
            </Card>
            </Col>
            <Col>
            <Card style = "background-color:#333;border-color:#333;width:8rem">
            <Label for="#">Out Time:</Label>
          
            <Input type="select" style = "width: 6rem;" bind:value="{outtime}">

                {#each Array(24)  as _,i}
                    <option value="{i}">{i}</option>
                {/each}
            </Input>

        </Card>
            </Col>
            </div>
        </Row>
    </Container>
        </FormGroup>
            <button class="search" type = "submit" on:click="{search}" ><b>Search</b></button>
        </form>
    </div>
    <br /><br />
     
    {#if fl == true}
        <Container>
            <Row cols={3} style="margin-bottom: 5px;">
                {#each arr as a}
                    <Col style="margin-bottom: 10px;">
                        <div class="search-container">
                         
                            <Card
                                class="slots"
                                body
                                color="{brr[a.slotnum]}"
                                style="width: 18rem;display:inline-block;"
                            >
                              <CardHeader>
                                  <CardTitle>  <h3>{a.slotnum}</h3></CardTitle>
                                </CardHeader>
                                {#if brr[a.slotnum]=='danger'}
                                    <p>Free after {frr[a.slotnum]}hr(s) of check-in</p>
                                {/if}

                                <h4>Cost = {cost}Rs.</h4>
                              
                            </Card>
                        </div>
                    </Col>
                {/each}
            </Row>
        </Container>
    {/if}
     
    <style>
        * {
            display: flex;
            flex-direction: column;
            
            justify-content: center;
        }
        .searchbar-container {
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            border-radius: 5px;
        }
        
        .search {
            border-color: #333 !important;
            color: lawngreen;
            background-color: #333;
            text-align: center !important;
        }
     
        span {
            color: lawngreen;
            background-color: #212529;
        }
     
        button {
            background-color: lawngreen !important;
            color: #333 !important;
            border-radius: 5px;
        }
     
        .mybox {
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
     
        input {
            height: 5vh;
        }
     
     
        .searchicon {
            margin-left: 5px;
            margin-right: 5px;
            background-color: #212529;
        }
     
        .btn_btn-default {
            height: 30px;
            width: 30px;
            background-color: #212529 !important;
            border-color: #212529 !important;
        }
     
        .hello {
            display: inline-block;
            border-radius: 5px;
        }
     
        .name {
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            color: lawngreen;
        }
    </style>
     