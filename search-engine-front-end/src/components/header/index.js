import { Link } from "react-router-dom";
import { routes } from "../../router/routes";
const Header = () => {
	return (
		<header style={{ marginBottom: "60px " }}>
			<nav className="navbar navbar-expand-lg navbar-light bg-white fixed-top">
				<div className="w-75 container-fluid">
					<div className="collapse navbar-collapse" id="navbarExample01">
						<ul className="navbar-nav me-auto mb-2 mb-lg-0">
							<li className="nav-item ">
								<Link className="mdi mdi-chart-bar nav-link " to={routes.CREATE_JOB_APPLICATION}>
									Apply for job
								</Link>
							</li>
						</ul>
					</div>
				</div>
			</nav>
		</header>
	);
};

export default Header;
