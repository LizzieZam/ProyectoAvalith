import React, { Component } from 'react';
import axios from 'axios';
import { withStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableHead from '@material-ui/core/TableHead';
import TableBody from '@material-ui/core/TableBody';
import TableRow from '@material-ui/core/TableRow';
import TableCell from '@material-ui/core/TableCell';
const styles = theme => ({
  root: {
    display: 'flex',
    flexWrap: 'wrap',
    justifyContent: 'space-around',
    overflow: 'hidden',
    backgroundColor: theme.palette.background.paper,
  },
  titulosTabla:
  {
    fontWeight: 'bold',
    color: 'DodgerBlue',
    padding: '40px',
    fontFamily: 'Arial',
    textAlign: 'center',
  },
  cuerpoTabla:
  {

    padding: '40px',
    fontFamily: 'Arial',
    textAlign: 'center',
  },

  table: {
    background:
      'linear-gradient(to top, rgba(0,0,0,0.7) 0%, ' +
      'rgba(0,0,0,0.3) 70%, rgba(0,0,0,0) 100%)',
    width: "80%",


  },

});
class Listado extends Component {
  state = {
    productos: [],
    pro: [],
    toDashboard: false,
  }
  componentDidMount() {
    axios.get("http://localhost:8080/hotel/habitaciones/").then(res => {
      const productos = res.data;
      this.setState({ productos });
    });
  }
  render() {
    const { classes } = this.props;
    return (

      <Paper className={classes.root}>
        
        <Table className={classes.table}>
          <TableHead>
            <TableRow>
              <TableCell className={classes.titulosTabla} >DESCRIPCION</TableCell>
              <TableCell className={classes.titulosTabla}>NUM. CAMAS</TableCell>
              <TableCell className={classes.titulosTabla}>PRECIO </TableCell>
              <TableCell className={classes.titulosTabla}>ESTADO </TableCell>
            </TableRow>
          </TableHead>
          <TableBody id='cuerpoTabla' >
            {
              this.state.productos.map(producto => {
                return (
                  <TableRow key={producto.idHabitacion} >
                    <TableCell className={classes.cuerpoTabla}>
                      {producto.descripcion}
                    </TableCell>
                    <TableCell className={classes.cuerpoTabla}>
                      {producto.numCamas}
                    </TableCell>
                    <TableCell className={classes.cuerpoTabla}>
                      {producto.precio}
                    </TableCell>
                    <TableCell className={classes.cuerpoTabla}>
                      {producto.estado}
                    </TableCell>
                  </TableRow>
                );
              })
            }
          </TableBody>
        </Table>
      </Paper>
    );
  }
}
export default withStyles(styles)(Listado);