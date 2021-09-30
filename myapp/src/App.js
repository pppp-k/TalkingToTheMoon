import "./App.css";
import axios from "axios";
import styled from "styled-components";

const Hello = styled.div`
  width: 400px;
  height: 400px;
  background-color: #000;
`;
function App() {
  return (
    <div className="App">
      <Hello></Hello>
    </div>
  );
}

export default App;
