    <script>
        import { Router, navigate } from "svelte-navigator";
        import {
            Label,
            Input,
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
        import { Col, Container, Row } from "sveltestrap";
        import { Column, Table } from "sveltestrap";
        import { userName, userId } from "./store";
     
        let isOpen = false;
        function handleUpdate(event) {
            isOpen = event.detail.isOpen;
        }
        const nav1 = () => {
            navigate("Userdash");
        }
     
        let f2 = false;
        let f4 = false;
        let money = 1000;
        let amount;
     
        let bookings = []
        async function getbookings(){
            f4 = true;
            f2 = false;


            let s = "http://localhost:8080/user/"+$userId
            
            let res = await fetch(s, {
     
                method: "GET",
                headers : {
                    "Content-type": "application/json",
                    "Accept" : "application/json",
                    "Access-Control-Allow-Origin": "http://localhost:8080"
                }
            })


            let us = await res.json()
            let bid =[]
            bid = us.bookingids
            for(let i=0;i<bid.length;i++){

                //bid[i] is booking id


                s = "http://localhost:8080/bookings/"+bid[i]

                let res2 = await fetch(s, {
     
                method: "GET",
                headers : {
                    "Content-type": "application/json",
                    "Accept" : "application/json",
                    "Access-Control-Allow-Origin": "http://localhost:8080"
                }
                    })

                let temp = await res2.json()
                if(temp!=null)
                    bookings.push(temp)


            }

            console.log(bookings)

            document.getElementById("myTable1").deleteTFoot();
        }
     
        async function money_wallet(){
            f4 = false;
            f2 = true;
            

            let s = "http://localhost:8080/user/"+$userId
            
            let res = await fetch(s, {
     
                method: "GET",
                headers : {
                    "Content-type": "application/json",
                    "Accept" : "application/json",
                    "Access-Control-Allow-Origin": "http://localhost:8080"
                }
            })


            let u = await res.json()

            money = u.balance





        }
     
        async function add_money(){
            money = money + amount;

            let s = "http://localhost:8080/add_money/"+$userId+"/"+money
            
            let res = await fetch(s, {
     
                method: "GET",
                headers : {
                    "Content-type": "application/json",
                    "Accept" : "application/json",
                    "Access-Control-Allow-Origin": "http://localhost:8080"
                }
            })

            
        }
     
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
                        <NavLink href="https://github.com/an-bhv/carp"
                            >GitHub</NavLink
                        >
                    </NavItem>
                    <Dropdown nav inNavbar>
                        <DropdownToggle nav caret>Options</DropdownToggle>
                        <DropdownMenu end>
                            <DropdownItem
                                ><a href="#" on:click={nav1}
                                    >Dashboard</a
                                ></DropdownItem
                            >
                            <DropdownItem
                                ><a href="/"
                                    >LogOut</a
                                ></DropdownItem
                            >
                        </DropdownMenu>
                    </Dropdown>
                </Nav>
            </Collapse>
        </Navbar>
    </div>
     
    <br />
    <div class="bro">
        <Container>
            <Row cols={2}>
                <Col>
                    <Card
                        style="background-color:#333;color:lawngreen;border-color:#333;width:15rem;margin-right:5px;text-align:center"
                    >
                        <button
                            style="color:lawngreen;background-color:#333;border-color:#333"
                            on:click|preventDefault = {getbookings}
                        >
                            Your Bookings
                        </button>
                    </Card>
                </Col>
                <br />
     
                <Col>
                    <Card
                        style="background-color:#333;color:lawngreen;border-color:#333;width:15rem;margin-right:5px;text-align:center"
                    >
                        <button
                            style="color:lawngreen;background-color:#333;border-color:#333"
                            on:click|preventDefault = {money_wallet}
                            >Recharge Wallet</button
                        >
                    </Card>
                </Col>
            </Row>
        </Container>
    </div>
     
    <!-- bookings table -->
    {#if f4 == true}
        <br>
        <h4 align="center" style="color:#333 !important"><b>All bookings</b></h4>
        <br />
        <div
            class="one"
            style="border-radius:5px !important; width: 60rem; align-self: center;"
        >
            <Table
                responsive
                rows={bookings}
                let:row
                style="color:lawngreen;"
                id="myTable1"
            >
                <Column header="Slot ID" width="8rem">
                    {row.slotid}
                </Column>
                <Column header="Space" width="8rem">
                    {row.space}
                </Column>
                <Column header="In time" width="8rem">
                    {row.intime}
                </Column>
                <Column header="Out time" width="8rem">
                    {row.outtime}
                </Column>
                <Column header="Date" width="8rem">
                    {row.date}
                </Column>
                <Column header="Services" width="8rem">
                    {row.services}
                </Column>
            </Table>
        </div>
        <br />
    {/if}
     
    {#if f2 == true}
    <br>
    <h4 align-self = "center" style = "color:#333;text-align:center"><b>Current balance: Rs. {money}</b></h4>
    <br>
    <div class = "box" style="background-color: #333 !important;">
        <Label>Enter amount</Label>
        <input type = "number" bind:value={amount}/>
        <button on:click|preventDefault = {add_money} style = "background-color:lawngreen; border-color:lawngreen; color: #333;">Add money</button>
    </div>
    {/if}
     
    <style>
        p {
            text-align: center;
        }
     
        button {
            text-align: center;
            color: lawngreen;
            background-color: #333;
            border-radius: 5px;
            border-color: #333;
            text-align: center;
        }
        * {
            display: flex;
            flex-direction: column;
     
            justify-content: center;
        }
        .one {
            background-color: #333 !important;
            border-color: #333;
            color: lawngreen;
        }
        .searchbar-container {
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            border-radius: 5px;
        }
     
        span {
            color: lawngreen;
            background-color: #212529;
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
     
        .box {
            width: 300px;
            border: 1px solid #aaa;
            border-radius: 2px;
            box-shadow: 2px 2px 8px rgba(0, 0, 0, 0.1);
            padding: 1em;
            margin: auto;
            margin-bottom: 1em;
            text-align: center;
            position: relative;
        }
     
        .bro {
            align-self: center;
            text-align: center;
        }
     
        p {
            color: lawngreen;
        }
    </style>  