import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";
import toast from "react-hot-toast";

function App() {
  const [count, setCount] = useState(0);

  return (
    <div>
      Home
      <button onClick={() => toast.success("This is toast")}>Clickme</button>
    </div>
  );
}

export default App;
