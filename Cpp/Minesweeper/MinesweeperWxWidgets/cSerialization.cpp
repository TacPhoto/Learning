#include "cSerialization.h"

//#define SHOWMINES

bool cSerialization::serialize(std::ofstream& outputFile, int lvl, int clicked, int x, int y, int hints, wxButton* btn[], int nField[])
{
	outputFile << lvl << std::endl << clicked << std::endl << x << std::endl << y;
	outputFile << std::endl << hints << std::endl;

	for (int i = 0; i < 100; i++)
	{
		int x = nField[i] == -1 ? 9 : 0;
		outputFile << x << ',' << btn[i]->IsEnabled() << ',' << btn[i]->GetLabelText() << std::endl;

	}
	
	return true;
}

bool cSerialization::deSerialize(std::ifstream &inputFile, int &lvl, int &clicked, int& x, int& y, int &hints, wxButton* btn[], int nField[])
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
		nField[i] = (line[0] == '9') ? -1 : 0;

		if (line[2] == '0')
		{
			btn[i]->Enable(false);
		}
		else
		{
			btn[i]->Enable(true);
		}

		if (line.length() > 4)
		{
			btn[i]->SetLabelText(line[4]);
		}
		else {
			btn[i]->SetLabelText("");
		}

#ifdef SHOWMINES
		if (nField[i] == -1)
			btn[i]->SetLabel("X");
#endif
		if (i == 100)
			break;

		i++;
	}



	return true;
}

