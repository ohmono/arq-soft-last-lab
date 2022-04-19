import React, { useState } from 'react';
import { HiTrash, HiPencilAlt, HiXCircle } from 'react-icons/hi';
import { BsPersonCircle } from 'react-icons/bs';
import ReactDOM from 'react-dom';
import { PersonForm } from './PersonForm';
import axios from 'axios';

export const PersonInfo = ({
	idPerson,
	firstName,
	lastName,
	email,
	salary,
	charge,
	address,
	office,
	dependency,
	hiringDate,
	updateList
}) => {
	const [showEditForm, setShowEditForm] = useState(false);

	const toggleShowForm = () => {
		setShowEditForm(!showEditForm);
	};

	const handleDelete = () => {
		axios
			.delete(`http://localhost:8089/persona/delete/${idPerson}`)
			.then(res => {
				console.log(res);
				updateList();
			});
	};

	const handleUpdate = person => {
		axios
			.put(`http://localhost:8089/persona/update/${idPerson}`, person)
			.then(res => {
				console.log(res);
				updateList();
			});
		setShowEditForm(false);
	};

	return (
		<div className="rounded-xl border m-2 grow shrink-0 overflow-hidden max-w-md shadow-lg">
			<div className="flex gap-2 items-center p-3 bg-slate-200">
				<BsPersonCircle size={'35px'} />
				{firstName} {lastName}																																																																															
				<div className="flex gap-2 ml-auto">
					<button
						onClick={toggleShowForm}
						className="bg-sky-500 rounded-full w-8 h-8 flex justify-center 
							items-center hover:outline transition-all">
						<HiPencilAlt size="1.2rem" />
					</button>
					<button
						onClick={handleDelete}
						className="bg-red-600 rounded-full w-8 h-8 flex justify-center 
							items-center hover:outline transition-all">
						<HiTrash size="1.2rem" />
					</button>
				</div>
			</div>
			<hr className="border-[1px]" />
			<div className="px-4 py-3">
				<p>
					<span className="font-bold">Email:</span> {email}
				</p>
				<p>
					<span className="font-bold">Salario:</span> ${salary}
				</p>
				<p>
					<span className="font-bold">Cargo:</span> {charge}
				</p>
				<p>
					<span className="font-bold">Dirección:</span> {address}
				</p>
				<p>
					<span className="font-bold">Oficina:</span> {office}
				</p>
				<p>
					<span className="font-bold">Dependencia:</span> {dependency}
				</p>
				<p>
					<span className="font-bold">Contratado en:</span> {hiringDate}
				</p>
			</div>

			{showEditForm &&
				ReactDOM.createPortal(
					<div className="bg-black bg-opacity-50 w-full h-full fixed top-0 right-0 left-0 z-50 flex justify-center items-center">
						<div className="relative bg-white rounded-lg p-6">
							<PersonForm
								onSubmit={handleUpdate}
								person={{
									idPerson,
									firstName,
									lastName,
									email,
									salary,
									charge,
									address,
									office,
									dependency,
									hiringDate
								}}
							/>
							<button
								className=" absolute top-1 right-1"
								onClick={toggleShowForm}>
								<HiXCircle size={'35px'} />
							</button>
						</div>
					</div>,
					document.getElementById('modal-root')
				)}
		</div>
	);
};
