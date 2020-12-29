#pragma once

#include "wx/wx.h"

class cMenuBar : public wxMenuBar
{
public:
    cMenuBar(const wxString& title);

    void OnQuit(wxCommandEvent& event);

    wxMenuBar* menubar;
    wxMenu* file;

};

