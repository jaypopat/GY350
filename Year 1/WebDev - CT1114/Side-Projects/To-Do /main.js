const newItem = document.getElementById("new-item");
const addBtn = document.getElementById("add-btn");
const list = document.getElementById("list");
const deleteBtn = document.getElementById("delete-btn");

// Function to add a new item to the list
const addItem = (event) => {
  event.preventDefault();
  // Create a new list item with a checkbox and label
  const inputValue = newItem.value.trim();
  if (inputValue !== "") {
    const li = document.createElement("li");
    const checkbox = document.createElement("input");
    checkbox.type = "checkbox";
    const label = document.createElement("label");
    label.textContent = inputValue;
    li.appendChild(checkbox);
    li.appendChild(label);
    list.appendChild(li);
    newItem.value = "";
  }
};
// Function to mark an item as completed
const completeItem = (event) => {
  // Check if the clicked element is an input checkbox
  if (event.target.type === "checkbox") {
    // Find the list item
    const item = event.target.parentElement;
    if (item) { // check whether the element that triggered the event is an input element.
      item.className = "completed";
    }
  }
};
// Function to delete all completed items
const deleteCompleted = () => {
  const completedItems = list.querySelectorAll(".completed");
  completedItems.forEach((item) => item.remove());
};
