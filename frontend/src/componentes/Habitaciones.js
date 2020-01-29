import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
import FormControl from '@material-ui/core/FormControl';
import InputLabel from '@material-ui/core/InputLabel';
import Select from '@material-ui/core/Select';
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
    formControl: {
        margin: theme.spacing(1),
        minWidth: 120,
    },
});
class Habitaciones extends Component {
    constructor(props) {
        super(props);

        this.state = {
            habitaciones: [],
            descripcion: '',
            estado: '',
            precio: '',
            numCamas: '',
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
            descripcion: this.state.descripcion,
            estado: this.state.estado,
            precio: this.state.precio,
            numCamas: this.state.numCamas,

        };
        console.log(Habitacion);
        axios.post('http://localhost:8080/hotel/habitaciones/', Habitacion).then(res => {
            console.log(res);
            console.log(res.data);
            axios.get('http://localhost:8080/hotel/habitaciones/').then(res => {
                const habitaciones = res.data;
                this.setState({ habitaciones });
            });
            this.setState({ toDashboard: true });

        });


    }

    componentDidMount() {
        axios.get('http://localhost:8080/hotel/habitaciones/').then(res => {
            const habitaciones = res.data;
            this.setState({ habitaciones });
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
                <AppBar>INGRESE UNA NUEVA HABITACION</AppBar>
                <br />
                <br />
                <br />
                <TextField
                    label="Descripcion"
                    className={classes.textField}
                    onChange={this.handleChange('descripcion')}
                />
                <FormControl className={classes.formControl}>
                    <InputLabel shrink id="demo-simple-select-placeholder-label-label">
                        Estado
        </InputLabel>
                    <Select
                        labelId="demo-simple-select-placeholder-label-label"
                        id="demo-simple-select-placeholder-label"
                        value={this.state.estado}
                        onChange={this.handleChange('estado')}
                        displayEmpty
                        className={classes.selectEmpty}
                    >
                        <option value="">
                            <em> </em>
                        </option >
                        <option value={"DISPONIBLE"}>DISPONIBLE</option >
                        <option value={"LIMPIEZA"}>LIMPIEZA</option >
                        <option value={"MANTENIMIENTO"}>MANTENIMIENTO</option >
                        <option value={"OCUPADA"}>OCUPADA</option >
                    </Select>

                </FormControl>

                <TextField
                    label="Precio"
                    type="number"
                    onChange={this.handleChange('precio')}
                    className={classes.textField}


                />
                <TextField
                    label="numCamas"
                    value={this.state.numCamas}
                    onChange={this.handleChange('numCamas')}
                    type="number"
                    className={classes.textField}
                    InputLabelProps={{
                        shrink: true,
                    }}
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
                                <TableCell className={classes.titulosTabla} >DESCRIPCION</TableCell>
                                <TableCell className={classes.titulosTabla}>NUM. CAMAS</TableCell>
                                <TableCell className={classes.titulosTabla}>PRECIO </TableCell>
                                <TableCell className={classes.titulosTabla}>ESTADO </TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody id='cuerpoTabla' >
                            {
                                this.state.habitaciones.map(habitacion => {
                                    return (
                                        <TableRow key={habitacion.idHabitacion} >
                                            <TableCell className={classes.cuerpoTabla}>
                                                {habitacion.descripcion}
                                            </TableCell>
                                            <TableCell className={classes.cuerpoTabla}>
                                                {habitacion.numCamas}
                                            </TableCell>
                                            <TableCell className={classes.cuerpoTabla}>
                                                {habitacion.precio}
                                            </TableCell>
                                            <TableCell className={classes.cuerpoTabla}>
                                                {habitacion.estado}
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
export default withStyles(styles)(Habitaciones);