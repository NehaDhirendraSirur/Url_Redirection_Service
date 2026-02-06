import { Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Stats from "./pages/Stats";
import NotFound from "./pages/NotFound";
import Navbar from "./components/Navbar";

function App() {
  return (
    <>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/stats/:shortCode" element={<Stats />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </>
  );
}

export default App;
