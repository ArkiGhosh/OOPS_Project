<script>
        import { Router, navigate } from "svelte-navigator";
        import { Alert, Form, FormGroup, FormText, Input, Label, ListGroup, ListGroupItem } from "sveltestrap";
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
import { each, space } from "svelte/internal";






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
                            <DropdownItem><a href="#" on:click="{()=>navigate("Profile")}">Profile</a></DropdownItem>
                            <DropdownItem><a href="#" on:click="{()=>navigate("Home")}">Log Out</a></DropdownItem>
                        </DropdownMenu>
                    </Dropdown>
                </Nav>
            </Collapse>
        </Navbar>
    </div>




   
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
     