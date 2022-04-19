import React, { useState } from 'react';
import { FaRegSave } from 'react-icons/fa';

export const PersonForm = ({ onSubmit, person }) => {
	const [form, setForm] = useState(
		person
			? person
			: {
					firstName: '',
					lastName: '',
					email: '',
					salary: '',
					charge: '',
					address: '',
					office: '',
					dependency: '',
					hiringDate: ''
				}
	);

	const handleChange = e => {
		setForm({ ...form, [e.target.name]: e.target.value });
	};

	const handleSubmit = e => {
		console.log(form);
		e.preventDefault();
		onSubmit(form);
		setForm({
			firstName: '',
			lastName: '',
			email: '',
			salary: '',
			charge: '',
			address: '',
			office: '',
			dependency: '',
			hiringDate: ''
		});
	};

	return (
		<form
			onSubmit={handleSubmit}
			className={' flex flex-col p-5 rounded-md gap-2'}>
			<FormInput
				label="Nombres"
				type="text"
				name="firstName"
				value={form.firstName}
				onChange={handleChange}
			/>

			<FormInput
				label="Apellidos"
				type="text"
				name="lastName"
				value={form.lastName}
				onChange={handleChange}
			/>

			<FormInput
				label="Email"
				type="email"
				name="email"
				value={form.email}
				onChange={handleChange}
			/>

			<FormInput
				label="Salario"
				type="number"
				name="salary"
				value={form.salary}
				onChange={handleChange}
				step="any"
				min={0}
				max={1000000}
			/>

			<FormInput
				label="Cargo"
				type="text"
				name="charge"
				value={form.charge}
				onChange={handleChange}
			/>

			<FormInput
				label="Dirección"
				type="text"
				name="address"
				value={form.address}
				onChange={handleChange}
			/>

			<FormInput
				label="Oficina"
				type="text"
				name="office"
				value={form.office}
				onChange={handleChange}
			/>

			<FormInput
				label="Dependencia"
				type="text"
				name="dependency"
				value={form.dependency}
				onChange={handleChange}
			/>

			<FormInput
				label="Fecha de contratación"
				type="date"
				name="hiringDate"
				value={form.hiringDate}
				onChange={handleChange}
			/>
			<hr className="border-[1px] my-2" />
			<button className="bg-emerald-700 text-white w-32 m-auto py-2 rounded-xl flex justify-evenly">
				<p>Guardar</p> <FaRegSave size='20px'/>
			</button>
		</form>
	);
};

const FormInput = ({
	label,
	name,
	type = 'text',
	value,
	onChange,
	required = true,
	...rest
}) => {
	return (
		<div className="flex gap-4 mx-auto">
			<label className="grow-0 shrink-0 w-24" htmlFor={name}>
				{label}:
			</label>
			<input
				type={type}
				name={name}
				id={name}
				value={value}
				onChange={onChange}
				required={required}
				{...rest}
				className="border-2 grow rounded-lg "
			/>
		</div>
	);
};
