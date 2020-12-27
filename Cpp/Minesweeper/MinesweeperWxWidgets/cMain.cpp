#include "cMain.h"

wxBEGIN_EVENT_TABLE(cMain, wxFrame)

wxEND_EVENT_TABLE()

cMain::cMain() : wxFrame(nullptr, wxID_ANY, "Minesweeper", wxPoint(20, 20), wxSize(800, 600))
{
	btn = new wxButton * [nFieldWidth * nFieldHeight];
	wxGridSizer* grid = new wxGridSizer(nFieldWidth, nFieldHeight, 0, 0);

	nField = new int[nFieldWidth * nFieldHeight];

	wxFont font(24, wxFONTFAMILY_DEFAULT, wxFONTSTYLE_NORMAL, wxFONTWEIGHT_BOLD, false);

	for (int x = 0; x < nFieldWidth; x++)
	{
		for (int y = 0; y < nFieldHeight; y++)
		{
			int index = y * nFieldWidth + x;
			
			btn[index] = new wxButton(this, 10000 + (index));
			btn[index]->SetFont(font);
			grid->Add(btn[y * nFieldWidth + x], 1, wxEXPAND | wxALL);

			btn[index]->Bind(wxEVT_COMMAND_BUTTON_CLICKED, &cMain::OnButtonClicked, this);
			nField[index] = 0;
		}
	}

	this->SetSizer(grid);
	grid->Layout();

}

cMain::~cMain()
{
	delete[]btn;
}

void cMain::OnButtonClicked(wxCommandEvent& evt)
{
	// Get button coordinate
	int x = (evt.GetId() - 10000) % nFieldWidth;
	int y = (evt.GetId() - 10000) / nFieldWidth;

	// Populate minefield
	if (bFirstClick)
	{
		int mines = 30;

		while (mines)
		{
			int rx = rand() % nFieldWidth;
			int ry = rand() % nFieldHeight;
			int randIndex = ry * nFieldWidth + rx;

			if (nField[randIndex] == 0 && rx != x && ry != y)
			{
				nField[randIndex] = -1;
				mines--;
			}

			bFirstClick = false;
		}
	}

	//  Disable button, cannot be pressed again
	int index = y * nFieldWidth + x;
	btn[index]->Enable(false);

	// Mine clicked test
	if (nField[index] == -1)
	{
		btn[index]->SetLabel("X");
		wxMessageBox("GAME OVER");

		// Reset game
		bFirstClick = true;
		for (int x = 0; x < nFieldWidth; x++) {
			for (int y = 0; y < nFieldHeight; y++)
			{
				int index = y * nFieldWidth + x;

				nField[index] = 0;
				btn[index]->SetLabel("");
				btn[index]->Enable(true);
			}
		}
	}

	else
	{
		// Count neighbourmines
		int mine_count = 0;
		for (int i = -1; i < 2; i++)
		{
			for (int j = -1; j < 2; j++)
			{
				if ((x + 1 >= 0) && (x + i < nFieldWidth) && (y + j >= 0) && (y + j < nFieldHeight))
				{
					if (nField[(y + j) * nFieldWidth + (x + i)] == -1)
					{
						mine_count++;
					}
				}
			}

			// Update button label
			if (mine_count > 0)
			{
				btn[index]->SetLabel(std::to_string(mine_count));
			}
		}
	}

	evt.Skip();
}