#include "cMain.h"

wxBEGIN_EVENT_TABLE(cMain, wxFrame)
	EVT_BUTTON(600, OnButtonSaveClicked) // menuSave
	EVT_BUTTON(601, OnButtonMenuClicked) // menuLoad
	EVT_BUTTON(602, OnButtonRestartClicked) // menuRestart

	EVT_BUTTON(610, OnButtonEasyClicked) // menuEasy
	EVT_BUTTON(611, OnButtonNormalClicked) // menuNormal
	EVT_BUTTON(612, OnButtonHardClicked) // menuHard

	EVT_BUTTON(613, OnButtonShowHelpClicked) // menuShowHelp

wxEND_EVENT_TABLE()

cMain::cMain() : wxFrame(nullptr, wxID_ANY, "Minesweeper", wxPoint(20, 20), wxSize(800, 600))
{
	setMenuBar();

	btn = new wxButton * [nFieldWidth * nFieldHeight];
	wxGridSizer* grid = new wxGridSizer(nFieldWidth, nFieldHeight, 0, 0);

	nField = new int[nFieldWidth * nFieldHeight];

	wxFont font(24, wxFONTFAMILY_DEFAULT, wxFONTSTYLE_NORMAL, wxFONTWEIGHT_BOLD, false);

	// Fill minefield with buttons
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

	//this->SetLabel(wxT("Test"));

}

void cMain::setMenuBar()
{
	wxMenuBar* menuBar = new wxMenuBar;

	wxMenu* menuGameSession = new wxMenu;
	wxMenu* menuLevel = new wxMenu;

	wxMenuItem* menuSave = new wxMenuItem(menuGameSession, 600, wxT("&Save"));
	wxMenuItem* menuLoad = new wxMenuItem(menuGameSession, 601, wxT("&Load"));
	wxMenuItem* menuRestart = new wxMenuItem(menuGameSession, 602, wxT("&Reset"));

	wxMenuItem* menuEasy = new wxMenuItem(menuLevel, 610, wxT("&Easy"));
	wxMenuItem* menuNormal = new wxMenuItem(menuLevel, 611, wxT("&Normal"));
	wxMenuItem* menuHard = new wxMenuItem(menuLevel, 612, wxT("&Hard"));
	wxMenuItem* menuShowHelp = new wxMenuItem(menuLevel, 613, wxT("&Show Help"));

	menuGameSession->Append(menuSave);
	menuGameSession->Append(menuLoad);
	menuGameSession->Append(menuRestart);

	menuLevel->Append(menuEasy);
	menuLevel->Append(menuNormal);
	menuLevel->Append(menuHard);
	menuLevel->Append(menuShowHelp);

	menuBar->Append(menuGameSession, wxT("&Game Session"));
	menuBar->Append(menuLevel, wxT("&Level"));

	this->SetMenuBar(menuBar);
	menuBar->Show(true);	
}

cMain::~cMain()
{
	delete[]btn;
	delete nField;

	delete menuEasy;
	delete menuNormal;
	delete menuHard;
	delete menuShowHelp;

	delete menuSave;
	delete menuLoad;
	delete menuRestart;

	delete menuGameSession;
	delete menuLevel;

	delete menuBar;

}

void cMain::OnButtonClicked(wxCommandEvent& evt)
{
	// Get button coordinate
	int x = (evt.GetId() - 10000) % nFieldWidth;
	int y = (evt.GetId() - 10000) / nFieldWidth;
	int index = y * nFieldWidth + x;
	int mines = 30;

	// Populate minefield
	if (bFirstClick)
	{
		populateMinefield(mines, x, y);
	}

	//  Disable button, cannot be pressed again
	btn[index]->Enable(false);

	// Mine clicked test
	if (nField[index] == -1)
	{
		btn[index]->SetLabel("X");
		wxMessageBox("GAME OVER");

		resetGame();
	}

	else
	{
		updateNeighbouringMinesNum(x, y);
	}

	evt.Skip();
}

void cMain::resetGame()
{
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

void cMain::updateNeighbouringMinesNum(int x, int y)
{
	int index = y * nFieldWidth + x;
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

void cMain::populateMinefield(int amount, int x, int y)
{
	int mines = amount;
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

void cMain::OnButtonSaveClicked(wxCommandEvent& evt)
{
	evt.Skip();
}

void cMain::OnButtonMenuClicked(wxCommandEvent& evt)
{
	evt.Skip();
}

void cMain::OnButtonRestartClicked(wxCommandEvent& evt)
{
	evt.Skip();
}

void cMain::OnButtonEasyClicked(wxCommandEvent& evt)
{
	evt.Skip();
}

void cMain::OnButtonNormalClicked(wxCommandEvent& evt)
{
	evt.Skip();
}

void cMain::OnButtonHardClicked(wxCommandEvent& evt)
{
	evt.Skip();
}

void cMain::OnButtonShowHelpClicked(wxCommandEvent& evt)
{
	evt.Skip();
}