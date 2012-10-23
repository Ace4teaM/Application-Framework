#include "main.h"

int main(int argc,char** argv){

    QApplication app(argc, argv);
    MainWindow* client = new MainWindow();
    client->show();

    return app.exec();
}
/*
int libtest() {
  // Copy resource dll to temporary file.
  QFile::copy(":/lib/Foo.dll", QDir::temp().filePath("Foo.dll"));

  // Load the temporary file as a shared library.
  QLibrary foo_lib(QDir::temp().filePath("Foo.dll"));
  typedef int (*FooDelegate)(int);
  FooDelegate foo = (FooDelegate)foo_lib.resolve("foo");

  if (foo) {
    std::cout << "foo(13) = " << foo(13) << std::endl;
  }
}*/

CmdExit::CmdExit() : QCommand( "exit", "Quitter l'application", QIcon("data/icon/exit.png")){

}

/* command execution dispatch */
const QResult* CmdExit::exec(QObject* context){
    if(dynamic_cast<QApplication*>(context)){
        return this->quit((QApplication*)context);
    }
    return QResult::ERR_UNSUPORTED_CMD;
}

/* Quitte l'application principale */
const QResult* CmdExit::quit(QApplication* context){
    context->quit();
    return QResult::OK;
}

CmdAbout::CmdAbout() : QCommand("about", "A Propos", QIcon("data/icon/about.png")){

}

/* command execution dispatch */
const QResult* CmdAbout::exec(QObject* context){
    if(QMainWindow* pWindow = dynamic_cast<QMainWindow*>(context)){
        return this->showDialog(pWindow);
    }
    return QResult::ERR_UNSUPORTED_CMD;
}

/* Affiche une boite de dialogue */
const QResult* CmdAbout::showDialog(QMainWindow* context){
    QMessageBox::information(context, "About", "hello");
    return QResult::OK;
}

MainWindow::MainWindow(QMainWindow* parent, Qt::WFlags fl) : QMainWindow( parent, fl ){
    setWindowTitle("Sample application");
    setMinimumSize(300, 150);

    //commandes
    QMenu* menu = menuBar()->addMenu("group1");

    addCommand(menu,new CmdAbout());
    addCommand(menu,new CmdExit());

}

void MainWindow::addCommand(QMenu* menu, QCommand* cmd ){
    //item
    QAction* action = new QAction(cmd->getName(), this);
    action->setShortcuts(QKeySequence::New);
    action->setStatusTip(cmd->getDescription());
    connect(action, SIGNAL(triggered()), this, SLOT(execCmd()));

    //ajoute au menu
    menu->addAction(action);

}

void MainWindow::execCmd(/*QObject* obj*/){
    std::cout << "HELLO!!!!!!!!!!" <<std::endl;
}
