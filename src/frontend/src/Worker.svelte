    <script>
        import { Router, navigate } from "svelte-navigator";
        import { Form, FormGroup, FormText, Input, Label } from "sveltestrap";
        import { Column, Table } from "sveltestrap";
        import { userName, userId } from "./store";
        import { Icon, Styles } from "sveltestrap";
        import { Col, Container, Row } from "sveltestrap";
        import {onMount} from 'svelte';
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
        import { ButtonDropdown } from "sveltestrap";
    
        

        //Make it on page load






        let isOpen = false;
     
        function handleUpdate(event) {
            isOpen = event.detail.isOpen;
        }
     
        function clear() {
            document.getElementById("bruh").value = "";
            f3 = true;
        }
     
        let f1 = false;
        let f2 = false;
        let f3 = false;
        let f4 = false;
     
        let feedback = [];
     
        let bookings = [];
     
        
        onMount(

            async () =>{



            let s = "http://localhost:8080/get_worker/"+$userId
            
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
            console.log(bid)
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


             s = "http://localhost:8080/get_worker/"+$userId
            
            let res2 = await fetch(s, {
     
                method: "GET",
                headers : {
                    "Content-type": "application/json",
                    "Accept" : "application/json",
                    "Access-Control-Allow-Origin": "http://localhost:8080"
                }
            })


            let w = await res2.json()
            let coms = w.comments
            console.log(coms)
            

                for(let i =0;i<coms.length;i++){

                    let temp = {}
                    temp["feed"] = coms[i]
                    console.log(temp)
                        feedback.push(temp)

                }

            console.log(feedback)


        }
        )

        async function deletefooter2() {
            f1 = false;
            f2 = false;
            f3 = false;
            f4 = true;


            












            document.getElementById("myTable1").deleteTFoot();
        }
     
        async function deletefooter1() {
            f2 = true;
            f1 = false;
            f4 = false;



            


            document.getElementById("myTable").deleteTFoot();
        }
     
        const nav1 = () => {
            navigate("");
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
                        <NavLink href = "/">LogOut</NavLink>
                        
                </Nav>
            </Collapse>
        </Navbar>
    </div>
     
    <br />
    <h2 style="color: #333; align-self:center"><b>Welcome {$userName}!</b></h2>
    <br />
     
    <div class="bro">
        <Container>
            <Row cols={3}>
                <Col>
                    <Card
                        style="background-color:#333;color:lawngreen;border-color:#333;width:15rem;margin-right:5px;text-align:center"
                    >
                        <Button
                            style="color:lawngreen;background-color:#333;border-color:#333"
                            on:click={function () {
                                f1 = true;
                                f2 = false;
                                f4 = false;
                            }}
                        >
                            Available Services
                        </Button>
                        {#if f1 == true}
                            <p>Dry Cleaning $5</p>
                            <p>Car Washing $5</p>
                            <p>Tyre Fill $5</p>
                        {/if}
                    </Card>
                </Col>
                <br />
     
                <Col>
                    <Card
                        style="background-color:#333;color:lawngreen;border-color:#333;width:15rem;margin-right:5px"
                    >
                        <button
                            style="color:lawngreen;background-color:#333;border-color:#333"
                            on:click|preventDefault={deletefooter2}>Bookings</button
                        >
                    </Card>
                </Col>
     
                <Col>
                    <Card
                        style="background-color:#333;color:lawngreen;border-color:#333;width:15rem;margin-right:5px"
                    >
                        <button
                            style="color:lawngreen;background-color:#333;border-color:#333"
                            on:click|preventDefault={deletefooter1}>User feedback</button
                        >
                    </Card>
                </Col>
            </Row>
        </Container>
    </div>
    <br /><br />
     
    {#if f2 == true}
        <div
            class="one"
            style="border-radius:5px !important; width: 60rem; align-self: center;"
        >
            <Table
                responsive
                rows={feedback}
                let:row
                style="color:lawngreen;"
                id="myTable"
            >
                <Column header="Feedback" width="8rem">
                    {row.feed}
                </Column>
            </Table>
        </div>
    {/if}
     
    {#if f4 == true}
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
                <Column header="Slot" width="8rem">
                    {row.slotid}
                </Column>
                <Column header="In time" width="8rem">
                    {row.intime}
                </Column>
                <Column header="Out time" width="8rem">
                    {row.outtime}
                </Column>
                <Column header="Cost" width="8rem">
                    {row.cost}
                </Column>
                <Column header="Services" width="8rem">
                    {row.services}
                </Column>
            </Table>
        </div>
        <br />
    {/if}
     
    <style>
        p {
            text-align: center;
        }
        .box {
            align-self: center;
            color: lawngreen;
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
        }
     
        p {
            color: lawngreen;
        }
    </style>