# Inventory Management System

## What will the application do?

This application will:
- provide long term store inventory information
- automatically re-order items with low inventory

## Who will use it?

Retail/wholesale stores and warehouse managers

## Why is this project of interest to you

Recently taking a logistics and operations course has created an interest in inventory management.


## User Stories

As a user,
- I want to be able to add a new product to the inventory management system
- I want to be able to identify products which will need re-ordering soon
- I want to be able to edit the inventory count of a product
- I want to be able to retrieve the inventory count of a product
- I want to be able to save the inventory data to a file
- I want to be able to load the inventory data from a file


## Phase 4: Task 2
Robust design in Inventory class, findItem, getItemCount, addItem methods

## Phase 4: Task 4
- Create a superclass that the action managers would inherit to decrease coupling in between the managers
  and the InventoryInterface since they share similar functionalities that could be overridden
- Change the implementation of the items in the inventory from list to hashmap/set as they are all unique items, 
  speeding up access operations for larger inventories.