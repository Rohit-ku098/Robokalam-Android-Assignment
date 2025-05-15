package com.example.robokalam.view.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.robokalam.R
import com.example.robokalam.common.ApiResponse
import com.example.robokalam.common.Constants.TAG
import com.example.robokalam.model.Portfolio
import com.example.robokalam.view.components.QuoteCard
import com.example.robokalam.view.components.ScreenSkeleton
import com.example.robokalam.view.components.TopBar
import com.example.robokalam.model.Quote
import com.example.robokalam.model.UserModel
import com.example.robokalam.view.components.EditModal
import com.example.robokalam.view.components.PortfolioSection
import com.example.robokalam.view.components.ProjectEditModal
import com.example.robokalam.viewModel.AuthViewModel
import com.example.robokalam.viewModel.MainViewModel

@Composable
fun HomeScreen(
    authViewModel: AuthViewModel,
    mainViewModel: MainViewModel,
    onLogout: () -> Unit,
    navigateToAbout: () -> Unit
) {

    val currentUser by authViewModel.currentUser.collectAsState()
    val quotes by mainViewModel.quotes.collectAsState()
    val portfolio by mainViewModel.portfolio.collectAsState()

    val context = LocalContext.current
    var nameEditModalOpen by rememberSaveable { mutableStateOf(false) }
    var collegeEditModalOpen by rememberSaveable { mutableStateOf(false) }
    var skillsEditModalOpen by rememberSaveable { mutableStateOf(false) }
    var projectsEditModalOpen by rememberSaveable { mutableStateOf(false) }

    var nameInput by rememberSaveable { mutableStateOf(portfolio.name) }
    var collegeInput by rememberSaveable { mutableStateOf(portfolio.college) }
    var skillsInput by rememberSaveable { mutableStateOf(portfolio.skills) }
    var projectsTitle by rememberSaveable { mutableStateOf(portfolio.projects.first) }
    var projectsDescription by rememberSaveable { mutableStateOf(portfolio.projects.second) }


    LaunchedEffect(portfolio) {
        nameInput = portfolio.name
        collegeInput = portfolio.college
        skillsInput = portfolio.skills
        projectsTitle = portfolio.projects.first
        projectsDescription = portfolio.projects.second

    }

    ScreenSkeleton(
        topAppBar = {
            TopBar(
                title = "Welcome ${currentUser?.name} !",
                action = {
                    IconButton(
                        onClick = onLogout,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(shape = CircleShape)
                                .border(shape = CircleShape, width = 2.dp, color = MaterialTheme.colorScheme.onPrimary)
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_logout),
                                contentDescription = "Logout",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
            )
        }
    ) {

        Column(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when(quotes) {
                is ApiResponse.Loading -> {
                    Log.d(TAG, "HomeScreen: Loading")
                    CircularProgressIndicator()
                }

                is ApiResponse.Error -> {
                    Toast.makeText(context, (quotes as ApiResponse.Error).message, Toast.LENGTH_SHORT).show()
                }
                is ApiResponse.Success -> {
                    QuoteCard(
                        quote = Quote(
                            q = (quotes as ApiResponse.Success).data[0].q,
                            a = (quotes as ApiResponse.Success).data[0].a
                        )
                    )
                }
            }

        }

        Column(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Portfolio",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            PortfolioSection(
                title = "Name",
                onEdit = { nameEditModalOpen = true }
            ) {
                Text(
                    text = portfolio.name,
                    modifier = Modifier.padding(8.dp)
                )
            }

            PortfolioSection(
                title = "College",
                onEdit = { collegeEditModalOpen = true }
            ) {
                Text(
                    text = portfolio.college,
                    modifier = Modifier.padding(8.dp)
                )
            }

            PortfolioSection(
                title = "Skills",
                onEdit = { skillsEditModalOpen = true }
            ) {
                Text(
                    text = portfolio.skills,
                    modifier = Modifier.padding(8.dp)
                )
            }

            PortfolioSection(
                title = "Projects",
                onEdit = { projectsEditModalOpen = true }
            ) {

                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = portfolio.projects.first,
                        modifier = Modifier,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.padding(8.dp))

                    Text(
                        text = portfolio.projects.second,
                        modifier = Modifier,
                        fontSize = 14.sp,
                    )
                }
            }



            if(nameEditModalOpen) {
                EditModal(
                    title = "Name",
                    onDismiss = { nameEditModalOpen = false },
                    inputValue = nameInput,
                    onInputChange = { nameInput = it },
                    onConfirm = {
                        mainViewModel.insertPortfolio(
                            mainViewModel.portfolio.value.copy(name = it)
                        )
                    }
                )
            }

            if(collegeEditModalOpen) {
                EditModal(
                    title = "College",
                    inputValue = collegeInput,
                    onInputChange = { collegeInput = it },
                    onDismiss = { collegeEditModalOpen = false },
                    onConfirm = {
                        mainViewModel.insertPortfolio(
                            mainViewModel.portfolio.value.copy(college = it)
                        )
                    }
                )
            }

            if(skillsEditModalOpen) {
                EditModal(
                    title = "Skills",
                    inputValue = skillsInput,
                    onInputChange = { skillsInput = it },
                    onDismiss = { skillsEditModalOpen = false },
                    onConfirm = {
                        mainViewModel.insertPortfolio(
                            mainViewModel.portfolio.value.copy(skills = it)
                        )
                    }
                )
            }

            if(projectsEditModalOpen) {
                ProjectEditModal(
                    title = "Projects",
                    titleInput = projectsTitle,
                    descriptionInput = projectsDescription,
                    onTitleChange = { projectsTitle = it },
                    onDescriptionChange = { projectsDescription = it },
                    onDismiss = { projectsEditModalOpen = false },
                    onConfirm = {
                        mainViewModel.insertPortfolio(
                            mainViewModel.portfolio.value.copy(projects = Pair(projectsTitle, projectsDescription))
                        )
                    }
                )
            }


            Button(
                onClick = {
                    navigateToAbout()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "About RoboKalam",
                )
            }
        }

    }
}
