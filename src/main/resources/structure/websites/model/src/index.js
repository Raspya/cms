import ReactDOM from 'react-dom/client';
import {Route, Routes} from "react-router-dom";
import Test from "./page/Test";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <Routes>
        <Test
        <Route path={"/une-url"} element={<Layout children={<Page/>}/>} />
    </Routes>
);