const newItem = document.getElementById("new-item");
const addBtn = document.getElementById("add-btn");
const list = document.getElementById("list");
const deleteBtn = document.getElementById("delete-btn");

addBtn.addEventListener("click", (event)=>{
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
});
list.addEventListener("click", event => {
  // Check if the clicked element is an input checkbox
  if (event.target.tagName === "INPUT") {
    // Find the list item
    const item = event.target.parentElement;
    if (item) {
      item.className = "completed";
    }
  }
});
deleteBtn.addEventListener("click",() => {
  const completedItems = list.querySelectorAll(".completed");
  completedItems.forEach((item) => item.remove());
});