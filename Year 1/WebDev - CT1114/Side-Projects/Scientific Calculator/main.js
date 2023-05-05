const inputChar = (char) => {
  document.getElementById("display").value += char;
};
const clearDisplay = () => {
  document.getElementById("display").value = "";
};
const deleteLastChar = () => {
  const output = document.getElementById("display").value;
  document.getElementById("display").value = output.substring(0,output.length - 1);
};
const calculateResult = () => {
  const result = eval(document.getElementById("display").value);
  document.getElementById("display").value = result;
};
