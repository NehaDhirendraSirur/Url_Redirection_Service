import { useParams } from "react-router-dom";

const Stats = () => {
  const { shortCode } = useParams();

  return (
    <div className="container">
      <h2>URL Stats</h2>
      <p>Short Code: {shortCode}</p>
    </div>
  );
};

export default Stats;
