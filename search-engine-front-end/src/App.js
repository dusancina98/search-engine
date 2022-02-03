import "bootstrap/dist/css/bootstrap.css";
import { BrowserRouter as Router } from "react-router-dom";
import Header from "./components/header";
import PageRouter from "./router";

function App() {
	return (
		<Router>
			<Header />
			<PageRouter />
		</Router>
	);
}

export default App;
