#include "cMain.h"

wxBEGIN_EVENT_TABLE(cMain, wxFrame)
	EVT_MENU(600, OnButtonSaveClicked) // menuSave
	EVT_MENU(601, OnButtonMenuClicked) // menuLoad
	EVT_MENU(602, OnButtonRestartClicked) // menuRestart

	EVT_MENU(610, cMain::OnButtonEasyClicked) // menuEasy
	EVT_MENU(611, OnButtonNormalClicked) // menuNormal
	EVT_MENU(612, OnButtonHardClicked) // menuHard

	EVT_MENU(613, OnButtonShowHelpClicked) // menuShowHelp

wxEND_EVENT_TABLE()


cMain::cMain() : wxFrame(nullptr, wxID_ANY, labelBaseTitle, wxPoint(20, 20), wxSize(800, 600))
{
	wxString labelBaseTitle = wxT("Minesweeper");
	this->SetLabel(labelBaseTitle);

	setMenuBar();
	level lvl;
	setLevel(easy);

	struct lastClickedField lastClicked = {5, 5};

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

void cMain::setLevel(level l)
{
	lvl = l;
	wxString newTitle;// = wxT("");

	switch (lvl)
	{
	case easy:
		mines = 15;
		hints = 2;
		newTitle = _("Minesweeper - Easy | Hints: ") + wxString::Format(wxT("%d"), (int)hints);
		break;
	case normal:
		hints = 1;
		mines = 30;
		newTitle = _("Minesweeper - Normal | Hints: ") + wxString::Format(wxT("%d"), (int)hints);
		break;
	case hard:
		hints = 0;
		mines = 45;
		newTitle = _("Minesweeper - Hard | Hints: ") + wxString::Format(wxT("%d"), (int)hints);
		break;
	}

	this->SetLabel(newTitle);


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

	lastClicked.x = 5;
	lastClicked.y = 5;
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
	resetGame();

	evt.Skip();
}

void cMain::OnButtonEasyClicked(wxCommandEvent& evt)
{
	setLevel(easy);
	resetGame();
	evt.Skip();
}

void cMain::OnButtonNormalClicked(wxCommandEvent& evt)
{
	setLevel(normal);
	resetGame();

	evt.Skip();
}

void cMain::OnButtonHardClicked(wxCommandEvent& evt)
{
	setLevel(hard);
	resetGame();

	evt.Skip();
}

void cMain::OnButtonShowHelpClicked(wxCommandEvent& evt)
{
	evt.Skip();
}

