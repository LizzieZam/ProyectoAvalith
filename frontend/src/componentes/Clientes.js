import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import axios from 'axios';
import Paper from '@material-ui/core/Paper';
import AppBar from '@material-ui/core/AppBar';
import Table from '@material-ui/core/Table';
import TableHead from '@material-ui/core/TableHead';
import TableBody from '@material-ui/core/TableBody';
import TableRow from '@material-ui/core/TableRow';
import TableCell from '@material-ui/core/TableCell';
import { Redirect } from 'react-router-dom';
const styles = theme => ({


    root: {
        width: '100%',
        marginTop: theme.spacing.unit * 3,
        overflowX: 'auto',
        display: 'flex',
        flexWrap: 'wrap',
        justifyContent: 'space-around',
        overflow: 'hidden',
        backgroundColor: theme.palette.background.paper,
    },
    button: {
        margin: theme.spacing.unit,
        height: 35,
    },
    container: {
        display: 'flex',
        flexWrap: 'wrap',
        justifyContent: 'center',

    },
    textField: {
        marginLeft: theme.spacing.unit,
        marginRight: theme.spacing.unit,
        width: 200,
        marginTop: 0,
        marginBottom: 0,
    },
    contenedor:
    {
        align: 'center',
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
class Clientes extends Component {
    constructor(props) {
        super(props);

        this.state = {
            clientes: [],
            nombreCliente: '',
            apellidoCliente: '',
            dniCliente: '',
            celularCliente: '',
            ciudadOriCliente: '',
            toDashboard: false,

        };
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleChangePanel = this.handleChangePanel.bind(this);

    }
    handleChange = name => event => {
        this.setState({ [name]: event.target.value });
    };
    handleSubmit() {

        const Habitacion = {
            nombreCliente: this.state.nombreCliente,
            apellidoCliente: this.state.apellidoCliente,
            dniCliente: this.state.dniCliente,
            celularCliente: this.state.celularCliente,
            ciudadOriCliente: this.state.ciudadOriCliente,

        };
        console.log(Habitacion);
        axios.post('http://localhost:8080/hotel/clientes/', Habitacion).then(res => {
            console.log(res);
            console.log(res.data);
            axios.get('http://localhost:8080/hotel/clientes/').then(res => {
                const clientes = res.data;
                this.setState({ clientes });
            });
            this.setState({ toDashboard: true });

        });


    }

    componentDidMount() {
        axios.get('http://localhost:8080/hotel/clientes/').then(res => {
            const clientes = res.data;
            this.setState({ clientes });
        });
    };
    render() {
        const { classes } = this.props;
        if (this.state.toDashboard === true) {
            return <Redirect to={{
                pathname: '/reserva',
            }} />
        }
        return (
            <form className={classes.root}>
                <AppBar>INGRESE UNA NUEVO CLIENTE</AppBar>
                <br />
                <br />
                <br />
                <TextField
                    label="Nombre"
                    className={classes.textField}
                    onChange={this.handleChange('nombreCliente')}
                />
                <TextField
                    label="Apellido"
                    className={classes.textField}
                    onChange={this.handleChange('apellidoCliente')}
                />
                <TextField
                    label="Identificacion"
                    className={classes.textField}
                    onChange={this.handleChange('dniCliente')}
                />
                <TextField
                    label="Celular"
                    type="number"
                    onChange={this.handleChange('celularCliente')}
                    className={classes.textField}


                />
                <TextField
                    label="Ciudad de Origen"
                    value={this.state.ciudadOriCliente}
                    onChange={this.handleChange('ciudadOriCliente')}
                    className={classes.textField}

                    margin="normal"
                />
                <Button variant="contained" color="primary" className={classes.button} onClick={this.handleSubmit}>Guardar</Button>
                <br />
                <br />
                <br />
                <Paper className={classes.root}>
                    <Table className={classes.table}>
                        <TableHead>
                            <TableRow>
                                <TableCell className={classes.titulosTabla}>IDENTIFICACION</TableCell>
                                <TableCell className={classes.titulosTabla} >CLIENTE</TableCell>
                                <TableCell className={classes.titulosTabla}>CIUDAD  </TableCell>
                                <TableCell className={classes.titulosTabla}>CELULAR </TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody id='cuerpoTabla' >
                            {
                                this.state.clientes.map(cliente => {
                                    return (
                                        <TableRow key={cliente.idHabitacion} >
                                            <TableCell className={classes.cuerpoTabla}>
                                                {cliente.dniCliente}
                                            </TableCell>
                                            <TableCell className={classes.cuerpoTabla}>
                                                {cliente.nombreCliente}     {cliente.apellidoCliente}
                                            </TableCell>
                                            <TableCell className={classes.cuerpoTabla}>
                                                {cliente.ciudadOriCliente}
                                            </TableCell>
                                            <TableCell className={classes.cuerpoTabla}>
                                                {cliente.celularCliente}
                                            </TableCell>
                                        </TableRow>
                                    );
                                })
                            }
                        </TableBody>
                    </Table>
                </Paper>
            </form>

        );
    }
}
export default withStyles(styles)(Clientes);