import React from 'react';
import './App.css';
import Habitaciones from './componentes/Habitaciones';
import Clientes from './componentes/Clientes';
import { HashRouter as Router, Route } from 'react-router-dom';
import Listado from './componentes/Listado';
import Transicion from './componentes/Transicion';
import Login from './componentes/Login';



function App() {
  return (
   
    <Router basename="/hotel">
        <div className="App">
          <div className="App__Aside"></div>
          <div className="App__Form" >
              <Route exact path="/reserva" component={Listado}></Route>
              <Route exact path="/" component={Login}></Route>
              <Route exact path="/transicion" component={Transicion}></Route>
              <Route exact path="/habitaciones" component={Habitaciones}></Route>              
              <Route exact path="/Clientes" component={Clientes}></Route>              
          </div>
        </div>
      </Router>
  );
}

export default App;

