import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import axios from 'axios';
import AppBar from '@material-ui/core/AppBar';
import { Redirect } from 'react-router-dom';

const styles = theme => ({
    root: {
        width: '100%',
        marginTop: theme.spacing(6),
        overflowX: 'auto',
    },
    button: {
        margin: theme.spacing(2),
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

    heading: {
        fontSize: theme.typography.pxToRem(15),
        flexBasis: '33.33%',
        flexShrink: 0,
    },
    secondaryHeading: {
        fontSize: theme.typography.pxToRem(15),
        color: theme.palette.text.secondary,
    },
});
class Login extends Component {
    constructor(props) {
        super(props);

        this.state = {
            username: '',
            password: '',
            respuesta: [],
            toDashboard: false,

        };
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);

    }

    handleChange = name => event => {
        this.setState({ [name]: event.target.value });
    };
    handleSubmit() {

        const login = {
            username: this.state.username,
            password: this.state.password,
        };

        axios.post('http://localhost:8080/hotel/login/', login).then(res => {

            const respuesta = res.data;
            console.log(respuesta)
            this.setState({ respuesta });
            if (respuesta === 'OK')
                this.setState({ toDashboard: true });
            else
                alert("Usuario o Contraseña incorrectos")
        });


    }

    render() {
        const { classes } = this.props;

        if (this.state.toDashboard === true) {
            return <Redirect to={{
                pathname: '/reserva',

            }} />
        }

        return (
            <form>
                <AppBar>LOGIN</AppBar>
                <br />
                <br />
                <br />
                <TextField
                    label="Ingrese usuario"
                    className={classes.textField}
                    onChange={this.handleChange('username')}
                /><br />
                <TextField
                    label="Ingrese Contraseña"
                    type="password"
                    className={classes.textField}
                    onChange={this.handleChange('password')}
                /><br />
                <Button variant="contained" color="primary" className={classes.button} onClick={this.handleSubmit}>Guardar</Button>
                <br />
                <br />
                <br />
            </form>

        );
    }
}

export default withStyles(styles)(Login);