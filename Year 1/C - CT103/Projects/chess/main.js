document.addEventListener("DOMContentLoaded", function () {
  const board = document.getElementById("board");

  for (let i = 0; i < 8; i++) {
    const row = document.createElement("div");
    row.classList.add("row");

    for (let j = 0; j < 8; j++) {
      const square = document.createElement("div");
      square.classList.add("square");
      square.classList.add((i + j) % 2 === 0 ? "white" : "black");
      row.appendChild(square);
    }
    board.appendChild(row);
  }
});
