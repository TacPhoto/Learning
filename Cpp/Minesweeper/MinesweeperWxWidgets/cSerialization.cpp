#include "cSerialization.h"

bool cSerialization::serialize(std::ofstream& outputFile, int lvl, int clicked, int x, int y, int hints, wxButton* btn[])
{
	outputFile << lvl << std::endl << clicked << std::endl << x << std::endl << y;
	outputFile << std::endl << hints << std::endl;

	for (int i = 0; i < 100; i++)
	{
		outputFile << btn[i]->IsEnabled() << ',' << btn[i]->GetLabelText() << std::endl;
	}
	
	return true;
}

bool cSerialization::deSerialize(std::ifstream &inputFile, int &lvl, int &clicked, int& x, int& y, int &hints, wxButton* btn[])
{
	std::string line;

	std::getline(inputFile, line);
	lvl = std::stoi(line);
	std::getline(inputFile, line);
	clicked = std::stoi(line);
	std::getline(inputFile, line);
	x = std::stoi(line);
	std::getline(inputFile, line);
	y = std::stoi(line);
	std::getline(inputFile, line);
	hints = std::stoi(line);


	int i = 0;
	while (std::getline(inputFile, line))
	{
		if (line[0] == '0')
		{
			btn[i]->Enable(false);
		}

		if (line.length() > 2)
		{
			btn[i]->SetLabelText(line[2]);
		}
		else {
			btn[i]->SetLabelText("");
		}

		if (i == 100)
			break;

		i++;
	}



	return true;
}

