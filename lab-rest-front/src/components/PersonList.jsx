import React from 'react';
import { PersonInfo } from './PersonInfo';

export const PersonList = ({ list, updateList }) => {
	return (
		<div className="flex flex-wrap w-full justify-center items-center">
			{!list.length &&
				<div className="text-xl font-extrabold pt-6">
					No hay Empleados resgistrados
				</div>}
			{[...list]
				.reverse()
				.map(person =>
					<PersonInfo
						key={person.idPerson}
						{...person}
						updateList={updateList}
					/>
				)}
		</div>
	);
};
